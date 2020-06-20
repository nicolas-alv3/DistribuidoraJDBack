package com.DistribuidoraJD.model.exception;

public class LackOfStockException extends RuntimeException{
    public LackOfStockException(String message){
        super(message);
    }
}
