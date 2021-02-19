package com.example.library.demo.controller;

import com.example.library.demo.entity.PurchaseOrderRequest;
import com.example.library.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class TestController {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private TestService testService;

    private static final String K_CONTENT_TYPE = "application/json";
    private static final String K_PRODUCES_TYPE = "application/json;charset=UTF-8";


    @GetMapping("/home")
    public String home() {
        return "Welcome to Demo Application";
    }


    @GetMapping("/error")
    public String error() {

        return "error";
    }

    @GetMapping("/getOrderById")
    public ResponseEntity<PurchaseOrderRequest> getOrderById(@RequestParam Long Id) {
        return testService.getOrderById(Id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping(value = "/send/message/kafka", produces = K_PRODUCES_TYPE, consumes = K_CONTENT_TYPE)
    public void sendDataToKafka(@RequestParam(value = "topic") String topicName, @RequestParam(value = "key", required = false)
            String key, @RequestBody String message) {
        ListenableFuture<SendResult<String, String>> future = null;
        if (null != key) {
            future = kafkaTemplate.send(topicName, key, message);
        } else {
            future = kafkaTemplate.send(topicName, message);
        }
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }

}
