package br.com.simple_payment.notification;

import org.springframework.stereotype.Service;

import br.com.simple_payment.transaction.Transaction;

@Service
public class NotificationService {

    private final NotificationProducer notificationProducer;

    public NotificationService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }
    public void notify(Transaction transaction){
        notificationProducer.sendNotification(transaction);
    }
}
