package com.zeros.design.strategy;

/**
 * Created by 迹_Jason on 2017/6/24.
 * 普通消费者打折力度
 */
public class CommonConsumerDiscount implements Discount {
    public Double discountMoney(Double total) {
        return total * 0.98;
    }
}
