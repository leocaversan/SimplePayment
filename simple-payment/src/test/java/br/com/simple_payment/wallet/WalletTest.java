package br.com.simple_payment.wallet;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    private Wallet wallet = Wallet.builder()
            .cpf("5955414452")
            .email("espro@gmail.com")
            .full_name("espro 55 ")
            .type(1)
            .balance(BigDecimal.valueOf(50)).build();

    private BigDecimal transactionValue = BigDecimal.valueOf(20);


    @Test
    void debitTest() {
        Wallet walletDebit = wallet.debit(transactionValue);
        assertEquals(wallet.balance().subtract(transactionValue), walletDebit.balance());
        assertEquals(wallet.cpf(), walletDebit.cpf());
        assertEquals(wallet.email(), walletDebit.email());
        assertEquals(wallet.type(), walletDebit.type());
        assertEquals(wallet.full_name(), walletDebit.full_name());
    }

    @Test
    void creditTest() {
        Wallet walletCredit = wallet.credit(transactionValue);
        assertEquals(wallet.balance().add(transactionValue), walletCredit.balance());
        assertEquals(wallet.cpf(), walletCredit.cpf());
        assertEquals(wallet.email(), walletCredit.email());
        assertEquals(wallet.type(), walletCredit.type());
        assertEquals(wallet.full_name(), walletCredit.full_name());

    }
}