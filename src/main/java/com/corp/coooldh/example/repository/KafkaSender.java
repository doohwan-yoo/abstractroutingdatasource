package com.corp.coooldh.example.repository;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaSender {

    private KafkaProducer<String, String> producer = null;
    private HashMap<String, String> config = new HashMap<String, String>();


    public KafkaSender() {

        Map<String, Object> config = new HashMap<String, Object>();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("acks", "all");
        config.put("block.on.buffer.full", "true");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(config);
    }

    public void sendToTestTopic(String topic) {
        producer.send(new ProducerRecord<>("test", topic),
            (metadata, exception) -> {
                if (metadata != null) {
                    System.out.println(
                            "partition(" + metadata.partition() + "), offset(" + metadata.offset() + ")");
                } else {
                    exception.printStackTrace();
                }
            });

//        producer.flush();
//        producer.close();
    }
}
