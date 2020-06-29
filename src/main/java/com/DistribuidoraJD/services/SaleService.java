package com.DistribuidoraJD.services;

import com.DistribuidoraJD.model.ProductC;
import com.DistribuidoraJD.model.ProductCopy;
import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.persistence.SaleDAO;
import com.DistribuidoraJD.services.dto.SaleDTO;
import com.DistribuidoraJD.services.exception.BadSaleFormException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Scope(value = "session")
@Component(value = "saleService")
public class SaleService {
    @Autowired
    SaleDAO saleDao;
    @Autowired
    ProductService productService;

    @Transactional
    public Sale postSale(SaleDTO saleDTO) throws BadSaleFormException {
       Set<Optional<ProductC>> maybeProducts = saleDTO.getProductCodes().stream().map(productService::getByCode).collect(Collectors.toSet());

       if(maybeProducts.stream().allMatch(Optional::isPresent)){
           Set<ProductC> productCS = maybeProducts.stream().map(Optional::get).collect(Collectors.toSet());// Los obtiene
           Set<ProductCopy> productsCopy = productCS.stream().map(ProductC::copy).collect(Collectors.toSet());//Los copia
           Sale sale = new Sale(saleDTO.getClientName(),productsCopy);

           return saleDao.save(sale);
       }
       throw new BadSaleFormException();
    }

    public Optional<Sale> getById(long id) {
        Optional<Sale> maybeSale = saleDao.getById(id);
        if(maybeSale.isPresent()){
            maybeSale.get().getProducts();
        }
        return maybeSale;
    }

    public List<Sale> getAll() {
        return saleDao.getAll();
    }
}
