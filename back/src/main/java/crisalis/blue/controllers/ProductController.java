package crisalis.blue.controllers;

import crisalis.blue.models.Product;
import crisalis.blue.models.dto.ProductDTO;
import crisalis.blue.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@Controller
@RequestMapping("product")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }
    @PostMapping(value ="create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO create(@RequestBody  Product product)
    {
        return productService.create(product);
    }
    @GetMapping(value="read",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> read()
    {
        return productService.read();
    }
    @PutMapping(value="update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO update( @RequestBody Product product)
    {
        return productService.update(product);
    }
    @DeleteMapping(value="delete/{id}")
    public ProductDTO delete(@PathVariable(value="id") Long id)
    {
        return productService.delete(id);
    }
}
