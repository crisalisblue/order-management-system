package crisalis.blue.factories;

import crisalis.blue.models.Asset;
import crisalis.blue.models.Servicie;

import java.math.BigDecimal;

public class ServiceFactory extends ItemFactory{

    //Aplicando Singleton
    //instancia unica
    private static final ServiceFactory instancia = new ServiceFactory();
    //Constructor privado para evitar que se instancie externamente
    private ServiceFactory() {}
    //Metodo para acceder a la instancia unica
    public static ServiceFactory getInstance() {
        return instancia;
    }


    public Asset crearItem(){
        Servicie service = new Servicie();
        service.setSupportFee(BigDecimal.valueOf(0));
        service.setBaseAmount(BigDecimal.valueOf(0));
        return service;
    }
}
