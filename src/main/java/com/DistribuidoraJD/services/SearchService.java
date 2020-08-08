package com.DistribuidoraJD.services;

import com.DistribuidoraJD.model.ProductC;
import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.persistence.ProductDAO;
import com.DistribuidoraJD.persistence.SaleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope(value = "session")
@Component(value = "searchService")
public class SearchService {
    @Autowired
    SaleDAO saleDao;

    @Autowired
    ProductDAO productDao;

    public Page<ProductC> searchProduct(String name, Long code, Integer page) {
        return productDao.getLike(name,code,page);
    }

    public Page<Sale> searchSale(String name, long code, Integer page) {
        return saleDao.getLike(name,code,page);
    }
}