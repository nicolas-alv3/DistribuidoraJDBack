package com.DistribuidoraJD.model;

import com.DistribuidoraJD.model.exception.LackOfStockException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<SoldItem> items;
    @NotNull
    private LocalDate date;
    @NotNull
    private String details;
    @Transient
    private List<ProductC> products;

    public Sale(){
        items = new ArrayList<>();
        date=LocalDate.now();
        products=new ArrayList<>();
    }

    public Sale(Client cliente, List<SaleItem> list,String detail){
        items = new ArrayList<>();
        products=new ArrayList<>();
        this.addList(list);
        client = cliente;
        details = detail;
        date=LocalDate.now();
    }

    private void addList(List<SaleItem> list) {
        list.forEach(i -> this.addItem(i));
    }

    public Double getTotalPrice() {
        //Sumatory of all unitPrices
        return items.stream().map(SoldItem::getPrice).reduce(0d,Double::sum);
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

    public List<SoldItem> getItems() {return items;}

    public List<SoldItem> setItems() {return items;}

    public void addItem(SaleItem saleItem) {
        products.add(saleItem.getProduct());
        items.add(new SoldItem(saleItem.getProduct().copy(), saleItem.getAmount()));
    }

    private boolean isSufficientStock(SaleItem saleItem) {
        return saleItem.getAmount() <= saleItem.getProduct().getStock();
    }

    public int getAmountOfProducts() {
        return items.stream().map(SoldItem::getAmount).reduce(0,Integer::sum);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<ProductC> getProducts() {
        return this.products;
    }

    public void close() {
        for (int i = 0; i < products.size(); i++) {
            products.get(i).substractStock(items.get(i).getAmount());
        }
    }

}
