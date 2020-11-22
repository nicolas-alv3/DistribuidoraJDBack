package com.DistribuidoraJD.services.exception;

import com.DistribuidoraJD.model.exception.DistribuidoraJDException;


public class BadSaleFormException extends DistribuidoraJDException {
    public BadSaleFormException(){
        super("El formulario no esta bien escrito");
    }
}
