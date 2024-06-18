package com.scaler.productapi.exceptions;

public class ProductNotFoundException extends  Exception{
    public ProductNotFoundException(String message){
        super(message);
    }
}
