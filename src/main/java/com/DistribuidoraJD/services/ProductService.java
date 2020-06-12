package com.DistribuidoraJD.services;

import com.DistribuidoraJD.model.Product;
import com.DistribuidoraJD.persistence.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Scope(value = "session")
@Component(value = "productService")
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

    public Product save(Product product){
        return productDAO.save(product);
    }

    public boolean existProductWithCode(long code) {
        return productDAO.existProductWithCode(code);
    }

    public Optional<Product> getByCode(long code) {
        return productDAO.getByCode(code);
    }

    public boolean removeByCode(long code) {
        return productDAO.removeByCode(code);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
}
