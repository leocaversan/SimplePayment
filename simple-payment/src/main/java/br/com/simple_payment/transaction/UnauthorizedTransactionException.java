package br.com.simple_payment.transaction;

public class UnauthorizedTransactionException extends RuntimeException {
    public UnauthorizedTransactionException(String message){
        super(message);
    }
}
