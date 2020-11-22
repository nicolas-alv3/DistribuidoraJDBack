package com.DistribuidoraJD.services;

import com.DistribuidoraJD.model.ProductC;
import com.DistribuidoraJD.model.SaleItem;
import com.DistribuidoraJD.model.exception.LackOfStockException;
import com.DistribuidoraJD.persistence.ProductDAO;
import com.DistribuidoraJD.services.exception.ProductAlreadyExistException;
import com.DistribuidoraJD.services.exception.ProductNotFoundException;
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
        if(productDAO.existProductWithCode(productC.getCode()))
            throw new ProductAlreadyExistException("Ya existe un producto con ese codigo");
        if(productDAO.existProductWithName(productC.getName()))
            throw new ProductAlreadyExistException("Ya existe un producto con ese nombre");
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
        if(!productDAO.existProductWithCode(productC.getCode()))
            throw new ProductNotFoundException();
        Optional<ProductC> maybeProduct = productDAO.getByName(productC.getName());
        if(maybeProduct.isPresent() && productC.getCode() != maybeProduct.get().getCode()){
            throw new ProductAlreadyExistException("Ya existe un producto con ese nombre");
        }
        return productDAO.update(productC);
    }

    @Transactional
    public ProductC changeStock(long code, int quantity, boolean add) {
        Optional<ProductC> maybeProduct = productDAO.getByCode(code);
        if (maybeProduct.isPresent()) {
            ProductC productC = maybeProduct.get();
            if (add)
                productC.addStock(quantity);
            else{
                productC.substractStock(quantity);
            }
            return productDAO.save(productC);
        }
        else
            throw new ProductNotFoundException();
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

    public void saveAll(List<ProductC> products) {
        products.forEach(p -> save(p));
    }

    public void updateAll(List<ProductC> products) {
        products.forEach(p -> productDAO.save(p));
    }
}
