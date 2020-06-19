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
        if(correctProduct(product)){
            return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
        }
        return new ResponseEntity<>("Formulario mal escrito", HttpStatus.NOT_FOUND);
    }

    private boolean correctProduct(Product product) {
        return (product.getCode() != 0
                && product.getName()!= "" && product.getName() != null
                && product.getAmountPerPackage() > 0  && product.getAmountPerPackage() != null
                && product.getAmountForDiscount()> 0  && product.getAmountForDiscount() != null
                && product.getPackageDiscount()> 0  && product.getPackageDiscount() != null
                && product.getUnitPrice() > 0  && product.getUnitPrice() != null
                && product.getStock() >= 0  && product.getUnitPrice() != null
        );
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, value = "/product")
    public ResponseEntity updateProduct(@RequestBody Product product){
        if(!productService.existProductWithCode(product.getCode())){
            return new ResponseEntity<>("No existe un producto con ese código", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
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
