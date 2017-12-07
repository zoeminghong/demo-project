package com.example.kafka.conumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created on 2017/12/5.
 *
 * @author 迹_Jason
 */
@Component
public class Listener {

    private final Logger logger = LoggerFactory.getLogger(Listener.class);

    @KafkaListener(topics = "maxwell")
    public void processMessage(String content) {
        logger.info("=============Start============");
        logger.info(content);
        logger.info("=============END============");
        wirte(content);

    }


    private void wirte(String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("./demo.log", true)));
            out.write(content);
            out.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
