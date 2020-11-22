package com.DistribuidoraJD.model;

import javax.persistence.*;
import javax.validation.constraints.Positive;

public class SaleItem extends Item{

    public SaleItem(){};

    public SaleItem(ProductC product, int amount) {
        super(product,amount);
    }

    public ProductC getProduct(){
        return (ProductC) product;
    }
}
