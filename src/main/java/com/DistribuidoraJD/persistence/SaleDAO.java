package com.DistribuidoraJD.persistence;

import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.persistence.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SaleDAO {
    @Autowired
    private SaleRepository saleRepository;

    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    public Optional<Sale> getById(long id) {
        return saleRepository.findById(id);
    }

    public List<Sale> getAll() {
        return saleRepository.findAll();
    }
}
