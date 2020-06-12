package com.DistribuidoraJD.services;

import com.DistribuidoraJD.model.Product;
import com.DistribuidoraJD.persistence.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "productService")
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

    public Product save(Product product){
        return productDAO.save(product);
    }

    public Product getByName(String name) {
        return productDAO.getByName(name);
    }
}
