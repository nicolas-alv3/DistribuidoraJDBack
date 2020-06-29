package com.DistribuidoraJD.services.controller;

import com.DistribuidoraJD.model.ProductC;
import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.services.SaleService;
import com.DistribuidoraJD.services.dto.SaleDTO;
import com.DistribuidoraJD.services.exception.BadSaleFormException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@Scope(value = "session")
@Component(value = "saleController")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Transactional
    @RequestMapping(method = RequestMethod.POST, value = "/sale")
    public ResponseEntity postSale(@RequestBody @Valid SaleDTO saleDTO, BindingResult bindingResult) throws BadSaleFormException {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Hay errores en el formulario",HttpStatus.BAD_REQUEST);
        }
        try{
            return new ResponseEntity<>(saleService.postSale(saleDTO), HttpStatus.OK);
        }
        catch (BadSaleFormException e){
            return new ResponseEntity<>("Algun codigo de producto no existe",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sale/{id}")
    public ResponseEntity getSale(@PathVariable("id") long id) {
        Optional<Sale> maybeProduct = saleService.getById(id);
        if(!maybeProduct.isPresent()){
            return new ResponseEntity<>("No se encontro la venta",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(maybeProduct.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sale/all")
    public ResponseEntity getAllSale() {
        return new ResponseEntity<>(saleService.getAll(), HttpStatus.OK);
    }
}
