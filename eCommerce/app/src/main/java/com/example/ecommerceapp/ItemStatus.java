package com.example.ecommerceapp;

public enum ItemStatus
    {
        ORDERED, IN_SHIPPING, DELIVERED;

        public static String statusToString(ItemStatus itemStatus) {
            String status;

            if(itemStatus == ORDERED){
                status = "Ordered";
            } else if (itemStatus == IN_SHIPPING){
                status = "In Shipping";
            } else if(itemStatus == DELIVERED){
                status = "Delivered";
            } else {
                status = "Unavailable";
            }
            return status;
        }
    }
