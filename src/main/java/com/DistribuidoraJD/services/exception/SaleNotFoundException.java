package com.DistribuidoraJD.services.exception;

import com.DistribuidoraJD.model.exception.DistribuidoraJDException;

public class SaleNotFoundException extends DistribuidoraJDException {

    public SaleNotFoundException() {
        super("No encontramos una venta con ese codigo");
    }
}
