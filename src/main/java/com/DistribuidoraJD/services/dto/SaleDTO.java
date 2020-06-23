package com.DistribuidoraJD.services.dto;

import com.DistribuidoraJD.model.Product;
import java.util.List;

public class SaleDTO {
    private String clientName;
    private List<Long> productCodes;

    public List<Long> getProductCodes() {
        return productCodes;
    }


    public String getClientName() {
        return clientName;
    }
}
