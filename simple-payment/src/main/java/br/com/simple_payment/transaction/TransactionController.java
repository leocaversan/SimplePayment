package br.com.simple_payment.transaction;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("transaction")
public class TransactionController {

     private final TransactionService transactionService;
     public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
     }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
         return transactionService.create(transaction);
     }
     
    @GetMapping
    public ArrayList list(){
      return transactionService.getData();
   }

}
