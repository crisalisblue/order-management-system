package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.Product;
import crisalis.blue.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    public Product crearProducto(Product product)
    {
        if(product.checkEmpty())
            return productRepository.save(product);
        else throw new EmptyElementException("No tiene los campos cargados ");
    }
    public Product crearUnProducto(Product product)
    {
        if(product.checkEmpty())
            return productRepository.save(product);
        else  return null;
    }
    public List<Product> leerEntradas(Product product)
    {
        return productRepository.findAll().stream().collect(Collectors.toList());
    }


}
