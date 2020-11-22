package com.DistribuidoraJD.services.controller;

import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.model.SaleItem;
import com.DistribuidoraJD.model.exception.DistribuidoraJDException;
import com.DistribuidoraJD.services.SaleService;
import com.DistribuidoraJD.services.dto.SaleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@Scope(value = "session")
@Component(value = "saleController")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @RequestMapping(method = RequestMethod.POST, value = "/sale")
    public ResponseEntity postSale(@RequestBody @Valid SaleDTO saleDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Hay errores en el formulario",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(saleService.postSale(saleDTO), HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/sale/{id}")
    public ResponseEntity getSale(@PathVariable("id") long id) {
        return new ResponseEntity<>(saleService.getById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sale/all/{page}")
    public ResponseEntity getAllSale(@PathVariable int page) {
        return new ResponseEntity<>(saleService.getAll(page), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sale/delete/{code}")
    public ResponseEntity removeProdupostct(@PathVariable("code") long code) {
        if(!saleService.removeByCode(code)){
            return new ResponseEntity<>("No se encontro la venta",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Venta con código " + code+ " eliminado", HttpStatus.OK);
    }

}
