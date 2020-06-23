package com.DistribuidoraJD.persistence;

import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.persistence.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleDAO {
    @Autowired
    private SaleRepository saleRepository;

    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }
}
