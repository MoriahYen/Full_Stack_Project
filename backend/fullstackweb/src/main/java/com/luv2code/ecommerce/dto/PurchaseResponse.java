package com.luv2code.ecommerce.dto;


import lombok.Data;

@Data
// Use this class to send back a Java object as JSON
public class PurchaseResponse {

    private final String orderTrackingNumber;

}
