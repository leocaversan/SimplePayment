package br.com.simple_payment.transaction;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryTest {

    private Transaction transaction;

    @Test
    public void SaveTest(){

        Transaction transactionTest = Transaction.builder()
                .payer(1L)
                .payee(2L)
                .value(BigDecimal.valueOf(50)).build();
    }
}