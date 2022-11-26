package edu.neu.karanwadhwa.springecommerceapi.service;

import edu.neu.karanwadhwa.springecommerceapi.model.Product;
import edu.neu.karanwadhwa.springecommerceapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product){
       return productRepository.save(product);
    }

    public List<Product> createProducts(List<Product> products){
        return (List<Product>) productRepository.saveAll(products);
    }

    public List<Product> getProducts(){
        return (List<Product>) productRepository.findAll();
    }

    public Product getProductById(int id){
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductByName(String name){
        return productRepository.findByName(name);
    }

    public Product updateProduct(Product newItem){
        Product oldProduct = getProductById(newItem.getProductId());
        oldProduct.setName(newItem.getName());
        oldProduct.setPrice(newItem.getPrice());
        oldProduct.setQuantity(newItem.getQuantity());
        return productRepository.save(oldProduct);
    }

    public String deleteProductById(int id){
        productRepository.deleteById(id);
        return "Product deleted: #"+id;
    }
}
