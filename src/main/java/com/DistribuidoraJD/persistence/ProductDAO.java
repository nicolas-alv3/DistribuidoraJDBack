package com.DistribuidoraJD.persistence;

import com.DistribuidoraJD.model.Product;
import com.DistribuidoraJD.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDAO {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product getByName(String name) {
        return productRepository.getByName(name);
    }
}
