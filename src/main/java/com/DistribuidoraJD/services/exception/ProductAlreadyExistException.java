package com.DistribuidoraJD.services.exception;

import com.DistribuidoraJD.model.exception.DistribuidoraJDException;

public class ProductAlreadyExistException extends DistribuidoraJDException {
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
