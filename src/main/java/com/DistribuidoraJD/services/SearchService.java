package com.DistribuidoraJD.services;

import com.DistribuidoraJD.model.ProductC;
import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.persistence.ProductDAO;
import com.DistribuidoraJD.persistence.SaleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope(value = "session")
@Component(value = "searchService")
public class SearchService {
    @Autowired
    SaleDAO saleDao;

    @Autowired
    ProductDAO productDao;

    public List<ProductC> searchProduct(String name, Long code) {
        return productDao.getLike(name,code);
    }

    public List<Sale> searchSale(String name, long code) {
        return saleDao.getLike(name,code);
    }
}