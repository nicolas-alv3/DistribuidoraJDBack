package com.DistribuidoraJD.services;

import com.DistribuidoraJD.model.Product;
import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.persistence.SaleDAO;
import com.DistribuidoraJD.services.dto.SaleDTO;
import com.DistribuidoraJD.services.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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

    public Sale postSale(SaleDTO saleDTO) {
       List<Optional<Product>> maybeProducts = saleDTO.getProductCodes().stream().map(productService::getByCode).collect(Collectors.toList());
       if(maybeProducts.stream().allMatch(Optional::isPresent)){
           List<Product> products = maybeProducts.stream().map(Optional::get).collect(Collectors.toList());
           Sale sale = new Sale(saleDTO.getClientName(),products);
           return saleDao.save(sale);
       }
       throw new BadRequestException("Bad request");
    }
}
