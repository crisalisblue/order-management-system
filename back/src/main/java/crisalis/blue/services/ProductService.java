package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.Product;
import crisalis.blue.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
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
    public Product create(Product product)
    {
        if(product.checkEmpty())
            return productRepository.save(product);
        else  return null;
    }
    public List<Product> read()
    {
        return productRepository.findAll().stream().collect(Collectors.toList());
    }
    public Product update(Product product)
    {
        Optional<Product> aux = productRepository.findById(product.getId());
        if(aux.isPresent())
        {
            if(!product.getName().isEmpty())
                aux.get().setName(product.getName());
            if(product.getGarantia() != aux.get().getGarantia())
                aux.get().setGarantia(product.getGarantia());
            if(product.getMountBase() != aux.get().getMountBase())
                aux.get().setMountBase(product.getMountBase());
            return productRepository.save(aux.get());
        }
        throw new EmptyElementException("No existe la entrada que se quiso actualizar ");
    }
    public Product delete(Long id )
    {
       Optional<Product> aux = productRepository.findById(id);
       if(aux.isPresent())
       {
           productRepository.deleteById(id);
           return aux.get();
       }
       throw new EmptyElementException("El id pasado por parámetro no coincide con ninguna de las entradas ");
    }


}
