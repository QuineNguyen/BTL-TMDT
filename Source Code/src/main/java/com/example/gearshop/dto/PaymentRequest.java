package com.example.gearshop.dto;

public class PaymentRequest {
    private int orderId;
    private double amount;
    private String type; // optional for extra/missing endpoint

    public PaymentRequest() {}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}