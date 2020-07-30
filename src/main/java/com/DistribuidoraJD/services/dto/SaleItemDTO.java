package com.DistribuidoraJD.services.dto;

import javax.validation.constraints.Positive;

public class SaleItemDTO {
    @Positive
    private Integer amount;
    private Long code;

    public Integer getAmount() {
        return amount;
    }

    public Long getCode() {
        return code;
    }
}
