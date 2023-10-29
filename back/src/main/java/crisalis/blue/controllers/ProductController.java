package crisalis.blue.controllers;

import crisalis.blue.models.Product;
import crisalis.blue.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }
    @PostMapping(value ="create",produces = MediaType.APPLICATION_JSON_VALUE)
    public Product crearUnProducto(Product product)
    {
        return productService.crearUnProducto(product);
    }

}
