package com.example.ecommerceapp.models;

import com.example.ecommerceapp.ItemStatus;

public class Item {

    private int id;
    private int orderNumber;
    private String name;
    private int quantityOrdered;
    private int numInStock;
    private int numSold;
    private int numDamaged;
    private int numWrittenOff;
    private ItemStatus itemsStatus;
    private int purchasePrice;
    private int shippingPerItem;
    private int totalCostPerItem;
    private int sellPrice;

    public Item(int id, int orderNumber, String name, int quantityOrdered, int numInStock, int numSold, int numDamaged, int numWrittenOff, ItemStatus itemsStatus, int purchasePrice, int shippingPerItem, int sellPrice) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.name = name;
        this.quantityOrdered = quantityOrdered;
        this.numInStock = numInStock;
        this.numSold = numSold;
        this.numDamaged = numDamaged;
        this.numWrittenOff = numWrittenOff;
        this.itemsStatus = itemsStatus;
        this.purchasePrice = purchasePrice;
        this.shippingPerItem = shippingPerItem;
        this.sellPrice = sellPrice;

        this.totalCostPerItem = purchasePrice + shippingPerItem;
    }

    public Item(int id, String name, int sellPrice) {
        this.id = id;
        this.name = name;
        this.sellPrice = sellPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public int getNumInStock() {
        return numInStock;
    }

    public void setNumInStock(int numInStock) {
        this.numInStock = numInStock;
    }

    public int getNumSold() {
        return numSold;
    }

    public void setNumSold(int numSold) {
        this.numSold = numSold;
    }

    public int getNumDamaged() {
        return numDamaged;
    }

    public void setNumDamaged(int numDamaged) {
        this.numDamaged = numDamaged;
    }

    public int getNumWrittenOff() {
        return numWrittenOff;
    }

    public void setNumWrittenOff(int numWrittenOff) {
        this.numWrittenOff = numWrittenOff;
    }

    public ItemStatus getItemsStatus() {
        return itemsStatus;
    }

    public void setItemsStatus(ItemStatus itemsStatus) {
        this.itemsStatus = itemsStatus;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getShippingPerItem() {
        return shippingPerItem;
    }

    public void setShippingPerItem(int shippingPerItem) {
        this.shippingPerItem = shippingPerItem;
    }

    public int getTotalCostPerItem() {
        return totalCostPerItem;
    }

    public void setTotalCostPerItem(int totalCostPerItem) {
        this.totalCostPerItem = totalCostPerItem;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }
}
