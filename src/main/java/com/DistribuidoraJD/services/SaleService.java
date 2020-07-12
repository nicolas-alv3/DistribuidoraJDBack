package com.DistribuidoraJD.services;

import com.DistribuidoraJD.model.ProductC;
import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.model.SaleItem;
import com.DistribuidoraJD.persistence.SaleDAO;
import com.DistribuidoraJD.services.dto.SaleDTO;
import com.DistribuidoraJD.services.exception.BadSaleFormException;
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
    public Sale postSale(SaleDTO saleDTO, List<SaleItem> items) {
        Sale newSale = new Sale(saleDTO.getClient(),items,saleDTO.getDetails());
        productService.substractStock(items);
        return saleDao.save(newSale);
    }

    public List<SaleItem> fetchItems(SaleDTO saleDTO) {
        return saleDTO.getSaleItems().stream().map(si -> new SaleItem(fetchProduct(si.getCode()).get().copy(),si.getAmount())).collect(Collectors.toList());
    }

    public boolean checkIsValidSale(SaleDTO saleDTO) {
        //Check if all products exist
        List<Optional<ProductC>> maybeProducts = saleDTO.getSaleItems().stream().map(si -> fetchProduct(si.getCode())).collect(Collectors.toList());
        return maybeProducts.stream().allMatch(Optional::isPresent) ;
    }

    private Optional<ProductC> fetchProduct(Long code) {
        return productService.getByCode(code);
    }

    public Optional<Sale> getById(long id) {
        Optional<Sale> maybeSale = saleDao.getById(id);
        return maybeSale;
    }

    public Page<Sale> getAll(int page) {
        return saleDao.getAll(page);
    }
}
