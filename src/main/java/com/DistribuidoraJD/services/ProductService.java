package com.DistribuidoraJD.services;

import com.DistribuidoraJD.model.ProductC;
import com.DistribuidoraJD.model.exception.LackOfStockException;
import com.DistribuidoraJD.persistence.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
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
    public ProductC save(ProductC productC){
        return productDAO.save(productC);
    }

    public Optional<ProductC> getByCode(long code) {
        return productDAO.getByCode(code);
    }

    @Transactional
    public boolean removeByCode(long code) {
        return productDAO.removeByCode(code);
    }

    public Page<ProductC> getAllProducts(int page) {
        return productDAO.getAllProducts(page);
    }

    @Transactional
    public ProductC update(ProductC productC) {
        return productDAO.update(productC);
    }

    @Transactional
    public boolean changeStock(long code, int quantity, String op) {
        Optional<ProductC> maybeProduct = productDAO.getByCode(code);
        if(maybeProduct.isPresent()){
            ProductC productC = maybeProduct.get();
            switch(op) {
                case "add":
                    productC.addStock(quantity);
                    return true;
                case "substract":
                    try {
                        productC.substractStock(quantity);
                        return true;
                    }catch (LackOfStockException e){
                       return false;
                    }
            }
        }
        return false;
    }

    public List<String> getAllProductsNames() {
        return productDAO.getAllProductNames();
    }

    public Optional<ProductC> getByName(String name) {
        return productDAO.getByName(name);
    }

    public boolean existProductWithCode(long code) {
        return productDAO.existProductWithCode(code);
    }

    public boolean existProductWithName(String name) {
        return productDAO.existProductWithName(name);
    }
}
