package com.DistribuidoraJD.model;

import com.DistribuidoraJD.model.exception.LackOfStockException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class ProductCopy extends Product{

    public ProductCopy(){super();}
    public ProductCopy(long code, String name, Double unitPrice, Double packageDiscount, Integer amountForDiscount, Integer amountPerPackage, Integer stock) {
        super(code, name, unitPrice, packageDiscount, amountForDiscount, amountPerPackage, stock);
    }
    //Copy of the products, only structure in order to persist it
}
