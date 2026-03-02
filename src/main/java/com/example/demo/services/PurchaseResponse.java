package com.example.demo.services;

import lombok.Data;


public class PurchaseResponse {
    private final String orderTrackingNumber;

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public PurchaseResponse(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }
}
