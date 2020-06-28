package com.DistribuidoraJD.services.controller;


import com.DistribuidoraJD.model.Product;
import com.DistribuidoraJD.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;

@CrossOrigin
@RestController
@Scope(value = "session")
@Component(value = "productController")
public class ProductController {
    @Autowired
    private ProductService productService;


    @RequestMapping(method = RequestMethod.POST, value = "/product")
    public ResponseEntity postProduct(@RequestBody @Valid Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Error en el formulario",HttpStatus.BAD_REQUEST);
        }
        if(productService.existProductWithCode(product.getCode())){
            return new ResponseEntity("Ya existe un producto con ese código", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/product")
    public ResponseEntity updateProduct(@RequestBody Product product,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Error en el formulario",HttpStatus.BAD_REQUEST);
        }
        if(!productService.existProductWithCode(product.getCode())){
            return new ResponseEntity<>("No existe producto con ese codigo",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/product/{code}")
    public ResponseEntity getProduct(@PathVariable("code") long code) {
        Optional<Product> maybeProduct = productService.getByCode(code);
        if(!maybeProduct.isPresent()){
            return new ResponseEntity<>("No se encontro el producto",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(maybeProduct.get(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/product/delete/{code}")
    public ResponseEntity removeProduct(@PathVariable("code") long code) {
        if(!productService.removeByCode(code)){
            return new ResponseEntity<>("No se encontro el producto",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Producto con código " + code+ " eliminado", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allProducts")
    public ResponseEntity getAllProduct(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/product/changeStock/{code}")
    public ResponseEntity changeStock(@PathVariable("code") long code, @RequestBody HashMap<String,String> body){
        if(!productService.changeStock(code,Integer.parseInt(body.get("quantity")),body.get("op"))){
            return new ResponseEntity<>("No se encontro el producto",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Stock cambiado con exito", HttpStatus.OK);
    }

}
