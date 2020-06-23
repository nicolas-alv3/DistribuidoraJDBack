package com.DistribuidoraJD.services.controller;

import com.DistribuidoraJD.model.Product;
import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.services.ProductService;
import com.DistribuidoraJD.services.SaleService;
import com.DistribuidoraJD.services.dto.SaleDTO;
import com.DistribuidoraJD.services.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@CrossOrigin
@RestController
@Scope(value = "session")
@Component(value = "saleController")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Transactional
    @RequestMapping(method = RequestMethod.POST, value = "/Sale")
    public ResponseEntity postSale(@RequestBody SaleDTO saleDTO){
        try{
            return new ResponseEntity<>(saleService.postSale(saleDTO), HttpStatus.OK);
        }
        catch (BadRequestException e){
            return new ResponseEntity<>("Formulario mal escrito", HttpStatus.NOT_FOUND);
        }
    }

}
