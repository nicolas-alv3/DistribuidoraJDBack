package com.DistribuidoraJD.model;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
public class SoldItem extends Item{


    public SoldItem(){};

    public SoldItem(ProductCopy product, int amount) {
        super(product,amount);
    }
    @Override
    public ProductCopy getProduct(){
        return (ProductCopy) product;
    }

}
