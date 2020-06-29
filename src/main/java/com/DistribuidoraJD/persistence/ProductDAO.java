package com.DistribuidoraJD.persistence;

import com.DistribuidoraJD.model.ProductC;
import com.DistribuidoraJD.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<ProductC> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductC update(ProductC productC) {

        productRepository.deleteByCode(productC.getCode());

        return productRepository.save(productC);
    }
}
