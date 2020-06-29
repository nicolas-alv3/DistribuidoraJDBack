package com.DistribuidoraJD.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String clientName;
    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<ProductCopy> products;
    @NotNull
    private LocalDate date;

    public Sale(){
        products = new HashSet<>();
        date=LocalDate.now();
    }

    public Sale(String clientName, Set<ProductCopy> list){
        this.clientName = clientName;
        products = list;
        date=LocalDate.now();
    }

    public Set<ProductCopy> getProducts() {
        return this.products;
    }

    public Double getTotalPrice() {
        //Sumatory of all unitPrices
        return products.stream().map(p -> p.getUnitPrice()).mapToDouble(Double::doubleValue).sum();
    }

    public int getAmountOfProducts(){
        return products.size();
    }

    public void addProducts(Set<ProductCopy> list) {
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

    public Set<ProductCopy> getProductsCopy(){
        return products;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalDate getDate() {
        return date;
    }
}
