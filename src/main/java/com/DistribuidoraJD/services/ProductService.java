package com.DistribuidoraJD.services;

import com.DistribuidoraJD.model.Product;
import com.DistribuidoraJD.model.exception.LackOfStockException;
import com.DistribuidoraJD.persistence.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Scope(value = "session")
@Component(value = "productService")
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Transactional
    public Product save(Product product){
        return productDAO.save(product);
    }

    public boolean existProductWithCode(long code) {
        return productDAO.existProductWithCode(code);
    }

    public Optional<Product> getByCode(long code) {
        return productDAO.getByCode(code);
    }

    @Transactional
    public boolean removeByCode(long code) {
        return productDAO.removeByCode(code);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Transactional
    public Product update(Product product) {
        return productDAO.update(product);
    }

    @Transactional
    public boolean changeStock(long code, int quantity, String op) {
        Optional<Product> maybeProduct = productDAO.getByCode(code);
        if(maybeProduct.isPresent()){
            Product product = maybeProduct.get();
            switch(op) {
                case "add":
                    product.addStock(quantity);
                    return true;
                case "substract":
                    try {
                        product.substractStock(quantity);
                        return true;
                    }catch (LackOfStockException e){
                       return false;
                    }
            }
        }
        return false;
    }
}
