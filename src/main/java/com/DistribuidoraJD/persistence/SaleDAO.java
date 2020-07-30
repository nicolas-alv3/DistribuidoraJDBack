package com.DistribuidoraJD.persistence;

import com.DistribuidoraJD.model.ProductC;
import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.persistence.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Sale> getAll(int page) {
        Pageable pagination = PageRequest.of(page,15);
        return saleRepository.findAll(pagination);
    }

    public List<Sale> getLike(String name, long code) {
        return saleRepository.findByClientNameLike(name,code);
    }
}
