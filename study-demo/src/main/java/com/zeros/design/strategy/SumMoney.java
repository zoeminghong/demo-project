package com.zeros.design.strategy;

/**
 * Created by 迹_Jason on 2017/6/24.
 * 调用策略接口
 */
public class SumMoney {

    Double sum(Discount consumerType) {
        Double total = 7 * 2 + 5.3 * 8;
        return consumerType.discountMoney(total);
    }

    public static void main(String[] args) {
        Discount consumerType = new CommonConsumerDiscount();
        SumMoney sumMoney = new SumMoney();
        System.out.println(sumMoney.sum(consumerType));
    }
}
