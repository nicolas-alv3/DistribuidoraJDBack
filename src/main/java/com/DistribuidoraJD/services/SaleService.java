package com.DistribuidoraJD.services;

import com.DistribuidoraJD.model.ProductC;
import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.model.SaleItem;
import com.DistribuidoraJD.persistence.SaleDAO;
import com.DistribuidoraJD.services.dto.SaleDTO;
import com.DistribuidoraJD.services.exception.BadSaleFormException;
import com.DistribuidoraJD.services.exception.ProductNotFoundException;
import com.DistribuidoraJD.services.exception.SaleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Scope(value = "session")
@Component(value = "saleService")
public class SaleService {
    @Autowired
    SaleDAO saleDao;
    @Autowired
    ProductService productService;

    @Transactional
    public Sale postSale(SaleDTO saleDTO) {
        List <SaleItem> items = fetchItems(saleDTO);
        Sale newSale = new Sale(saleDTO.getClient(),items,saleDTO.getDetails());
        productService.updateAll(newSale.getProducts());
        newSale.close();
        return saleDao.save(newSale);
    }

    public List<SaleItem> fetchItems(SaleDTO saleDTO) {
        return saleDTO.getSaleItems().stream().map(
                si -> new SaleItem(fetchOrRaise(si.getCode()), si.getAmount())
                )
        .collect(Collectors.toList());
    }

    private ProductC fetchOrRaise(Long code) {
        Optional<ProductC> maybeProduct = fetchProduct(code);
        if(maybeProduct.isPresent())
            return maybeProduct.get();
        else
            throw new ProductNotFoundException();
    }

    public boolean checkIsValidSale(SaleDTO saleDTO) {
        //Check if all products exist
        List<Optional<ProductC>> maybeProducts = saleDTO.getSaleItems().stream().map(si -> fetchProduct(si.getCode())).collect(Collectors.toList());
        return maybeProducts.stream().allMatch(Optional::isPresent) ;
    }

    private Optional<ProductC> fetchProduct(Long code) {
        return productService.getByCode(code);
    }

    public Sale getById(long id) {
        Optional<Sale> maybeSale = saleDao.getById(id);
        if (maybeSale.isPresent())
            return maybeSale.get();
        else
            throw new SaleNotFoundException();

    }

    public Page<Sale> getAll(int page) {
        return saleDao.getAll(page);
    }

    @Transactional
    public boolean removeByCode(long code) {
        return saleDao.removeByCode(code);
    }
}
