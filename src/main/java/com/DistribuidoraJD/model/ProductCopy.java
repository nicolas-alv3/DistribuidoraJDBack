package com.DistribuidoraJD.model;

import javax.persistence.*;

@Entity
public class ProductCopy extends Product{

    public ProductCopy() {}
    
    public ProductCopy(long code, String name, Double unitPrice, Double packageDiscount, Integer amountForDiscount, Integer amountPerPackage, Integer stock, ProductCategory category) {
        super(code, name, unitPrice, packageDiscount, amountForDiscount, amountPerPackage, stock, ProductCategory.valueOf("GALLETITAS"));
    }
    //Copy of the products, only structure in order to persist it
}
