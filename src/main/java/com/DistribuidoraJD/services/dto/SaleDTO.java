package com.DistribuidoraJD.services.dto;

import com.DistribuidoraJD.model.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SaleDTO {
    @NotNull
    private String clientName;
    @NotEmpty
    private List<Long> productCodes;

    public List<Long> getProductCodes() {
        return productCodes;
    }


    public String getClientName() {
        return clientName;
    }
}
