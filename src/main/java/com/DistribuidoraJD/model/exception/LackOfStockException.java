package com.DistribuidoraJD.model.exception;

public class LackOfStockException extends DistribuidoraJDException{
    public LackOfStockException(){
        super("No tienes suficiente stock");
    }
}
