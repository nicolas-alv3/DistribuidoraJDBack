package com.DistribuidoraJD.services.controller;

import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Scope(value = "session")
@Component(value = "searchController")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.GET, value = "/search/product/{data}/{page}")
    public ResponseEntity getProtSearch(@PathVariable("data") String data,@PathVariable("page") Integer page) {
        Long code;
        try{
            code = Long.parseLong(data);
        }catch (Exception e) {
            code = new Long(-1);
        }
        return new ResponseEntity<>(searchService.searchProduct(data,code,page), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/sale/{data}/{page}")
    public ResponseEntity getSaleSearch(@PathVariable("data") String data,@PathVariable("page") Integer page) {
        Long code;
        try{
            code = Long.parseLong(data);
        }catch (Exception e) {
            code = new Long(-1);
        }
        return new ResponseEntity<>(searchService.searchSale(data,code,page), HttpStatus.OK);
    }
}
