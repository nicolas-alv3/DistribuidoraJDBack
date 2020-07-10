package com.DistribuidoraJD.services.dto;

import com.DistribuidoraJD.model.Client;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SaleDTO {
    @NotNull
    private Client client;
    @NotNull
    private String details;
    @NotEmpty
    private List<SaleItemDTO> saleItems;

    public SaleDTO() {
    }

    public List<SaleItemDTO> getSaleItems() {
        return saleItems;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setSaleItems(List<SaleItemDTO> saleItems) {
        this.saleItems = saleItems;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
