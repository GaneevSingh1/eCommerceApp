package com.example.ecommerceapp.models;

public class Purchase {

    public enum PaymentMethod
    {
        TRANSFER, CASH
    };

    private int id;

    //refactor to Date() class
    private String date;

    private int customerID;
    private int itemID;
    private int quantity;
    private int soldPrice;
    private boolean delivery;
    private boolean paymentReceived;
    private boolean itemReceived;
    private PaymentMethod paymentMethod;

    public Purchase(int id, String date, int customerID, int itemID, int quantity, int soldPrice, boolean delivery, boolean paymentReceived, boolean itemReceived, PaymentMethod paymentMethod) {
        this.id = id;
        this.date = date;
        this.customerID = customerID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.soldPrice = soldPrice;
        this.delivery = delivery;
        this.paymentReceived = paymentReceived;
        this.itemReceived = itemReceived;
        this.paymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(int soldPrice) {
        this.soldPrice = soldPrice;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public boolean isPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(boolean paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public boolean isItemReceived() {
        return itemReceived;
    }

    public void setItemReceived(boolean itemReceived) {
        this.itemReceived = itemReceived;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
