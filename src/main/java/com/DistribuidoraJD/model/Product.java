package com.DistribuidoraJD.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private long id;
    private long code;
    private String name;
    private Double unitPrice;
    private Double packageDiscount; //porcentual
    private Integer amountForDiscount;
    private Integer amountPerPackage;
    private Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product(String name){

        this.name = name;
    }

    public Product(){}

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Integer getAmountPerPackage() {
        return amountPerPackage;
    }

    public void setAmountPerPackage(Integer amountPerPackage) {
        this.amountPerPackage = amountPerPackage;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPackageDiscount() {
        return packageDiscount;
    }

    public void setPackageDiscount(Double packageDiscount) {
        this.packageDiscount = packageDiscount;
    }

    public Integer getAmountForDiscount() {
        return amountForDiscount;
    }

    public void setAmountForDiscount(Integer amountForDiscount) {
        this.amountForDiscount = amountForDiscount;
    }
}
