package br.com.simple_payment.notification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "transaction-notification", groupId = "simple_payment")
    public void getMessage(String message){
        
        System.out.println(message);
    
    }
}
