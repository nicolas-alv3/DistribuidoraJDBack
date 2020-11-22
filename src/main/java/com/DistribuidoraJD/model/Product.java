package com.DistribuidoraJD.model;

import com.DistribuidoraJD.model.exception.LackOfStockException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
public abstract class Product {
    @Id
    @GeneratedValue
    protected long id;
    @Positive
    protected long code;
    @Size(min = 2, max = 50)
    protected String name;
    @Positive
    protected Double unitPrice;
    @PositiveOrZero
    protected Double packageDiscount; //porcentual
    @Positive
    protected Integer amountForDiscount;
    @Positive
    protected Integer amountPerPackage;
    @PositiveOrZero
    protected Integer stock;
    @NotNull
    protected ProductCategory category;

    public Product(long code, String name, Double unitPrice, Double packageDiscount, Integer amountForDiscount, Integer amountPerPackage, Integer stock, ProductCategory category){
        this.code = code;
        this.name = name;
        this.unitPrice = unitPrice;
        this.packageDiscount = packageDiscount;
        this.amountForDiscount = amountForDiscount;
        this.amountPerPackage = amountPerPackage;
        this.stock = stock;
        this.category = category;
    }

    public Product(){
        this.stock = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


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

    public void addStock(int n) {
        this.stock = stock + n;
    }

    public void substractStock(int n) {
        if(stock >= n){
            this.stock = stock - n;
        }
        else{
            throw new LackOfStockException();
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
