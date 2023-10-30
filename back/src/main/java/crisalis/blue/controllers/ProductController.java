package crisalis.blue.controllers;

import crisalis.blue.models.Product;
import crisalis.blue.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }
    @PostMapping(value ="create",produces = MediaType.APPLICATION_JSON_VALUE)
    public Product create(Product product)
    {
        return productService.create(product);
    }
    @GetMapping(value="read",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> read()
    {
        return productService.read();
    }
    @PutMapping(value="update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product update( @RequestBody Product product)
    {
        return productService.update(product);
    }
    @DeleteMapping(value="delete/{id}")
    public Product delete(@PathVariable Long id)
    {
        return productService.delete(id);
    }
}
