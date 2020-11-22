package com.DistribuidoraJD.services.exception;

import com.DistribuidoraJD.model.exception.DistribuidoraJDException;

public class ProductNotFoundException extends DistribuidoraJDException {
    public ProductNotFoundException(){
        super("No se ha encontrado producto con ese codigo");
    }
}
