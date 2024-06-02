package br.com.simple_payment.notification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    // private RestClient restClient;

    // public NotificationConsumer(RestClient.Builder builder){
    //     this.restClient = builder.baseUrl("").build();
    // }

    // @KafkaListener(topics = "transaction-notification", groupId = "simple_payment")
    // public void receiveNotification(Transaction transaction){
    //     var response = restClient.get()
    //             .retrieve()
    //             .toEntity(Notification.class);
    //     if (response.getStatusCode().isError() || response.getBody().message())
    //         throw new NotificationException("Error seding notification");

    // }
    @KafkaListener(topics = "transaction-notification", groupId = "simple_payment")
    public void getMessage(String message){
        
        System.out.println(message);
    
    }
}
