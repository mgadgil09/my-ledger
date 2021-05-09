package com.myledger.model.Enums;

public enum PaymentSourceType {
    CANARA("Canara Bank"), // Canara Bank
    SBT("State Bank of Travencore"), // State Bank of Travencore
    SBI("State Bank of India"), // State Bank of India
    CASH("Cash"), // Cash payment
    SALE("Sales Returns"); // Sales Returns

    private String paymentSourceType;

    public String getPaymentSourceType() {
        return this.paymentSourceType;
    }

    PaymentSourceType(String paymentSourceType){
        this.paymentSourceType = paymentSourceType;
    }

}
