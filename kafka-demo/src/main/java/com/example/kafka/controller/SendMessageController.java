package com.example.kafka.controller;

import com.example.kafka.produce.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2017/12/7.
 *
 * @author 迹_Jason
 */
@RestController
public class SendMessageController {

    @Autowired
    private Product product;

    @GetMapping("send/{payLoad}")
    public void sendMessage(@PathVariable String payLoad) {
        product.send("maxwell", payLoad);
    }
}
