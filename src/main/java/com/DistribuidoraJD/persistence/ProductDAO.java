package com.DistribuidoraJD.persistence;

import com.DistribuidoraJD.model.ProductC;
import com.DistribuidoraJD.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDAO {

    @Autowired
    private ProductRepository productRepository;

    public ProductC save(ProductC productC){
        return productRepository.save(productC);
    }

    public Optional<ProductC> getByCode(long code) {
        return productRepository.findByCode(code);
    }

    public boolean existProductWithCode(long code) {
        return productRepository.existsByCode(code);
    }

    public boolean removeByCode(long code) {
        if(existProductWithCode(code)){
            productRepository.deleteByCode(code);
            return true;
        }
        return false;
    }

    public Page<ProductC> getAllProducts(int page) {
        Pageable pagination = PageRequest.of(page,15);
        return productRepository.findAll(pagination);
    }

    public ProductC update(ProductC productC) {

        productRepository.deleteByCode(productC.getCode());

        return productRepository.save(productC);
    }

    public List<String> getAllProductNames() {
        return productRepository.getAllNames();
    }

    public Optional<ProductC> getByName(String name) {
        return productRepository.findByName(name);
    }

    public boolean existProductWithName(String name) {
        return productRepository.findByName(name).isPresent();
    }

    public Page<ProductC> getLike(String name, Long code, Integer page) {
        Pageable pagination = PageRequest.of(page,10);
        return productRepository.findByNameOrCode(name,code,pagination);
    }
}
