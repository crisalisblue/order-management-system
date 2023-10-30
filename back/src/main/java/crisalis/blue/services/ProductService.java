package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.Product;
import crisalis.blue.models.dto.ProductDTO;
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
            if(product.getMountBase() != aux.get().getMountBase())
                aux.get().setMountBase(product.getMountBase());
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
