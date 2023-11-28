package crisalis.blue.factories;

import crisalis.blue.models.Asset;
import crisalis.blue.models.Product;

import java.math.BigDecimal;

public class ProductFactory extends ItemFactory{

    //Aplicando Singleton
    //instancia unica
    private static final ProductFactory instancia = new ProductFactory();
    //Constructor privado para evitar que se instancie externamente
    private ProductFactory() {}
    //Metodo para acceder a la instancia unica
    public static ProductFactory getInstance() {
        return instancia;
    }

    @Override
    public Asset crearItem(){
        Product product = new Product();
        product.setBaseAmount(BigDecimal.valueOf(0));
        return product;
    }
}
