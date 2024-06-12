package br.com.simple_payment.transaction;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.simple_payment.authorization.AuthorizerService;
import br.com.simple_payment.notification.NotificationService;
import br.com.simple_payment.wallet.Wallet;
import br.com.simple_payment.wallet.WalletRepository;
import br.com.simple_payment.wallet.WalletType;

@Service
public class TransactionService {
    
    private  final TransactionRepository transactionRepository;
    private  final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository,
                              WalletRepository walletRepository,
                              AuthorizerService authorizerService,
                              NotificationService notificationService){
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;

    }


    public ArrayList getData() {

        if (!authorizerService.authorize())
            throw  new UnauthorizedTransactionException("Transaction not authorized");

        var list = this.transactionRepository.findAll();
        var listJsonReturn = new ArrayList();

        list.forEach(transaction -> {
            var data = new HashMap<>();

            data.put("id", transaction.id());
            data.put("payer", transaction.payer());
            data.put("payee", transaction.payee());
            data.put("value", transaction.value());
            data.put("createdAt", transaction.createdAt());

            listJsonReturn.add(data);
        });

        return listJsonReturn;
    }
    
    @Transactional
    public Transaction create(Transaction transaction){

        validate(transaction);

        if (!authorizerService.authorize())
            throw  new UnauthorizedTransactionException("Transaction not authorized");

        var walletPayer = walletRepository.findById(transaction.payer()).get();
        var walletPayee = walletRepository.findById(transaction.payee()).get();
        var newTransaction = transactionRepository.save(transaction);

        walletRepository.save(walletPayer.debit(transaction.value()));
        walletRepository.save(walletPayee.credit(transaction.value()));
        
        // notificationService.notify(transaction);
        
        return newTransaction;
    }

    private void validate(Transaction transaction){
        walletRepository.findById(transaction.payee())
                .map(payee -> walletRepository.findById(transaction.payee())
                        .map(payer -> isTransactionValid(transaction, payer) ? transaction : null)
                        .orElseThrow(() -> new InvalidTransactionException( "Invalid transaction insuficient amount or - %s".formatted(transaction) )))
                .orElseThrow(() -> new InvalidTransactionException( "Invalid transaction - %s".formatted(transaction) ));
    }
    private  boolean isTransactionValid(Transaction transaction, Wallet payer){
        return payer.type() == WalletType.COMUM.getValue() &&
                    payer.balance().compareTo(transaction.value()) >= 0 &&
                        !payer.id().equals(transaction.payer());
    }

}
