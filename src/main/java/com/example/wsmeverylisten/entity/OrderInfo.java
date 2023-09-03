package com.example.wsmeverylisten.entity;


import lombok.Data;

@Data
public class OrderInfo {


    private String code;

    private Long orderId;

    public OrderInfo(String code, Long orderId) {
        this.code = code;
        this.orderId = orderId;
    }
}
