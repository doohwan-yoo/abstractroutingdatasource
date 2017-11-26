package com.corp.coooldh.example.repository;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ReceiveWorker implements Runnable {

    KafkaConsumer consumer = null;

    public ReceiveWorker(KafkaConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(500);
            for (ConsumerRecord<String, String> record : records) {
                switch (record.topic()) {
                    case "test":
                        System.out.println("received");
                        System.out.println(record.value());
                        break;
                    default:
                        throw new IllegalStateException("get message on topic " + record.topic());
                }
            }
        }
    }
}
