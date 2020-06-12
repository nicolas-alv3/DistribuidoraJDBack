package com.DistribuidoraJD.services.controller;

import com.DistribuidoraJD.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Scope(value = "session")
@Component(value = "APIController")
public class APIController {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity apiTest() {
        return new ResponseEntity<>("API TESTING", HttpStatus.OK);
    }

}