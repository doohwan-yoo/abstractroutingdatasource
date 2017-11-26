package com.corp.coooldh.example.repository;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaReceiver {

    private KafkaConsumer<String, String> consumer = null;

    public KafkaReceiver() {

        System.out.println("created");

        Map<String, Object> config = new HashMap<String, Object>();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("group.id", "test");
        config.put("session.timeout.ms", "10000");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(config);

        consumer.subscribe(Arrays.asList("test"));

        Runnable rcWorker = new ReceiveWorker(consumer);
        Thread test = new Thread(rcWorker);

        test.start();

    }
}
