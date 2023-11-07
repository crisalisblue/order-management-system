package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.Product;
import crisalis.blue.models.dto.ProductDTO;
import crisalis.blue.repositories.ProductRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }
    public ProductDTO create(Product product)
    {
        if(product.checkEmpty())
            return productRepository.save(product).productToDTO();
        else throw new EmptyElementException("No tiene los campos cargados ");
    }

    public List<ProductDTO> read()
    {
        return productRepository.findAll().stream().map(Product::productToDTO).collect(Collectors.toList());
    }
    public ProductDTO update(Product product)
    {
        Optional<Product> aux = productRepository.findById(product.getId());
        if(aux.isPresent())
        {
            if(!product.getName().isEmpty())
                aux.get().setName(product.getName());
            if(product.getBaseAmount() != aux.get().getBaseAmount())
                aux.get().setBaseAmount(product.getBaseAmount());
            return productRepository.save(aux.get()).productToDTO();
        }
        throw new EmptyElementException("No existe la entrada que se quiso actualizar ");
    }
    public ProductDTO delete(Long id )
    {
        Optional<Product> aux = productRepository.findById(id);
        if(aux.isPresent())
        {
            productRepository.deleteById(id);
            return aux.get().productToDTO();
        }
        throw new EmptyElementException("El id pasado por parámetro no coincide con ninguna de las entradas ");
    }


}
