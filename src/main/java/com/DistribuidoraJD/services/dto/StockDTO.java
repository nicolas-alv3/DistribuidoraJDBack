package com.DistribuidoraJD.services.dto;

public class StockDTO {
    private Integer amount;
    private Boolean add;
    public StockDTO() {}

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getAdd() {
        return add;
    }

    public void setAdd(Boolean add) {
        this.add = add;
    }
}
