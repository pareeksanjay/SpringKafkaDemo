package com.example.library.demo.kafka.consumer;

import com.example.library.demo.dao.PurchaseOrderRepository;
import com.example.library.demo.entity.PurchaseOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LocalKafkaConsumer {
@Autowired
    PurchaseOrderRepository purchaseOrderRepository;


    @KafkaListener(topics = "${kafka.topicName}", groupId = "${kafka.groupId}")
    public void consumeMessage(String message) {
        System.out.println("Received Message: " + message);
        PurchaseOrderRequest purchaseOrderRequest=new PurchaseOrderRequest();
        purchaseOrderRequest.setName(message);
        purchaseOrderRepository.save(purchaseOrderRequest);

    log.info("PURCHASE ORDER INSERTED IN DB");
    }
}
