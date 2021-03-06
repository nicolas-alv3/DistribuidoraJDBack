package com.DistribuidoraJD.model;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
public class SaleItem {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne(cascade=CascadeType.ALL)
    private ProductCopy product;
    @Positive
    private int amount;

    public SaleItem(){};

    public SaleItem(ProductCopy product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public ProductCopy getProduct(){
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public Double getPrice() {
        return totalPrice() - discount();
    }

    private Double discount() {
        if (amount >= product.getAmountForDiscount()){
            return totalPrice() * porcentage();
        }
        return 0d;
    }

    private Double porcentage() {
        //Transform 25% in 0.25
        return product.getPackageDiscount() / 100;
    }

    @Transient
    public boolean isValidStock() {
        return product.getStock() >= amount;
    }

    private Double totalPrice() {
        return amount * product.getUnitPrice();
    }
}
