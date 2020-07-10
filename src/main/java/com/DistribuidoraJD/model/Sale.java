package com.DistribuidoraJD.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedEntityGraph(name = "Sale.detail",
        attributeNodes = @NamedAttributeNode("items"))
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade=CascadeType.ALL)
    Client client;
    //@JsonBackReference Con esto no te trae los datos de una
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<SaleItem> items;
    @NotNull
    private LocalDate date;
    @NotNull
    private String details;

    public Sale(){
        items = new ArrayList<>();
        date=LocalDate.now();
    }

    public Sale(Client cliente, List<SaleItem> list,String detail){
        client = cliente;
        items = list;
        details = detail;
        date=LocalDate.now();
    }

    public Double getTotalPrice() {
        //Sumatory of all unitPrices
        return items.stream().map(SaleItem::getPrice).reduce(0d,Double::sum);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<SaleItem> getItems() {return items;}

    public List<SaleItem> setItems() {return items;}

    public void addItem(SaleItem saleItem) {
        this.items.add(saleItem);
    }

    public int getAmountOfProducts() {
        return items.stream().map(SaleItem::getAmount).reduce(0,Integer::sum);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
