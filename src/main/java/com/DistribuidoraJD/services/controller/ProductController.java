package com.DistribuidoraJD.services.controller;


import com.DistribuidoraJD.model.Product;
import com.DistribuidoraJD.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Scope(value = "session")
@Component(value = "productController")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST, value = "/product")
    public Product postProduct(@RequestBody Product product){
        return productService.save(product);
    }

}
