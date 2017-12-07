package com.example.kafka.produce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created on 2017/12/7.
 *
 * @author 迹_Jason
 */
@Component
public class Product {

    private static final Logger LOGGER = LoggerFactory.getLogger(Product.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
        kafkaTemplate.send(topic,"test", payload);
    }
}
