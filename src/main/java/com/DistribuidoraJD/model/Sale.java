package com.DistribuidoraJD.model;

import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sale {
    @Id
    @GeneratedValue
    private long id;
    private String clientName;
    @OneToMany
    private List<Product> products;

    public Sale(){
        products = new ArrayList<>();
    }

    public Sale(String clientName, List<Product> list){
        this.clientName = clientName;
        products = list;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public Double getTotalPrice() {
        //Sumatory of all unitPrices
        return products.stream().map(p -> p.getUnitPrice()).mapToDouble(Double::doubleValue).sum();
    }

    public void addProducts(List<Product> list) {
        products.addAll(list);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
