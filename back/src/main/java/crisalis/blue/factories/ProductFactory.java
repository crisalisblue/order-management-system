package crisalis.blue.factories;

import crisalis.blue.models.Asset;
import crisalis.blue.models.Product;

import java.math.BigDecimal;

public class ProductFactory extends ItemFactory{

    @Override
    public Asset crearItem(){
        Product product = new Product();
        product.setBaseAmount(BigDecimal.valueOf(0));
        return product;
    }
}
