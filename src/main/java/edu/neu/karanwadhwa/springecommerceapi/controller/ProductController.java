package edu.neu.karanwadhwa.springecommerceapi.controller;

import edu.neu.karanwadhwa.springecommerceapi.model.Product;
import edu.neu.karanwadhwa.springecommerceapi.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/product/create")
    public Product createProduct(@RequestBody Product product){
        return service.createProduct(product);
    }

    @PostMapping("/product/create-multi")
    public List<Product> createProducts(@RequestBody List<Product> products){
        return service.createProducts(products);
    }

    @GetMapping("/product/all")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id){
        return service.getProductById(id);
    }

    @GetMapping("/product/{name}")
    public Product getProductByName(@PathVariable String name){
        return service.getProductByName(name);
    }

    @PutMapping("/product/update")
    public Product updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }

    @DeleteMapping("/product/delete/{id}")
    public String deleteProductById(@PathVariable int id){
        return service.deleteProductById(id);
    }

}
