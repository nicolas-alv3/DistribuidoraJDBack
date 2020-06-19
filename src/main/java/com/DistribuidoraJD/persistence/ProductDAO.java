package com.DistribuidoraJD.persistence;

import com.DistribuidoraJD.model.Product;
import com.DistribuidoraJD.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDAO {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> getByCode(long code) {
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

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product update(Product product) {

        productRepository.deleteByCode(product.getCode());

        return productRepository.save(product);
    }
}
