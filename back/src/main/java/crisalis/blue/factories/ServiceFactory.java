package crisalis.blue.factories;

import crisalis.blue.models.Asset;
import crisalis.blue.models.Servicie;

import java.math.BigDecimal;

public class ServiceFactory extends ItemFactory{
    public Asset crearItem(){
        Servicie service = new Servicie();
        service.setSupportFee(BigDecimal.valueOf(0));
        service.setBaseAmount(BigDecimal.valueOf(0));
        return service;
    }
}
