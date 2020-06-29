package com.DistribuidoraJD.model;

import com.DistribuidoraJD.model.exception.LackOfStockException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class ProductC extends Product{
    //Concrete product

    public ProductCopy copy() {
        return new ProductCopy(code,name,unitPrice,packageDiscount,amountForDiscount,amountPerPackage,stock);
    }
}
