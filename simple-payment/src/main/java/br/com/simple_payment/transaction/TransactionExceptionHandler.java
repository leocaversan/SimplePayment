package br.com.simple_payment.transaction;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class TransactionExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<Object> handle(InvalidTransactionException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    } 
    
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(UnauthorizedTransactionException.class)
    public ResponseEntity<Object> handle(UnauthorizedTransactionException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    } 

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handle(HttpClientErrorException e) {
        
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", "Transaction not authorized");

        return ResponseEntity.internalServerError().body(errorMap);
    } 

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handle(IllegalArgumentException e) {
        
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", "Invalid transaction body");

        return ResponseEntity.badRequest().body(errorMap);
    } 
}
