package com.DistribuidoraJD.services;

import com.DistribuidoraJD.model.Product;
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
       Set<Optional<Product>> maybeProducts = saleDTO.getProductCodes().stream().map(productService::getByCode).collect(Collectors.toSet());

       if(maybeProducts.stream().allMatch(Optional::isPresent)){
           Set<Product> products = maybeProducts.stream().map(Optional::get).collect(Collectors.toSet());// Los obtiene
           Set<ProductCopy> productsCopy = products.stream().map(Product::copy).collect(Collectors.toSet());//Los copia
           Sale sale = new Sale(saleDTO.getClientName(),productsCopy);

           return saleDao.save(sale);
       }
       throw new BadSaleFormException();
    }
}
