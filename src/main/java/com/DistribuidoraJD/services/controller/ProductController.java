package com.DistribuidoraJD.services.controller;


import com.DistribuidoraJD.model.Product;
import com.DistribuidoraJD.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@CrossOrigin
@RestController
@Scope(value = "session")
@Component(value = "productController")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Transactional
    @RequestMapping(method = RequestMethod.POST, value = "/product")
    public ResponseEntity postProduct(@RequestBody Product product){
        if(productService.existProductWithCode(product.getCode())){
            return new ResponseEntity<>("Ya existe un producto con ese código", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, value = "/product")
    public ResponseEntity updateProduct(@RequestBody Product product){
        if(!productService.existProductWithCode(product.getCode())){
            return new ResponseEntity<>("No existe un producto con ese código", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/product/{code}")
    public ResponseEntity getProduct(@PathVariable("code") long code){
        Optional<Product> maybeProduct = productService.getByCode(code);
        if(!maybeProduct.isPresent()){
            return new ResponseEntity<>("No existe un producto con ese código", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(maybeProduct.get(), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, value = "/product/delete/{code}")
    public ResponseEntity removeProduct(@PathVariable("code") long code){
        if(!productService.removeByCode(code)){
            return new ResponseEntity<>("No existe un producto con ese código", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Producto con código " + code+ " eliminado", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allProducts")
    public ResponseEntity getAllProduct(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

}
