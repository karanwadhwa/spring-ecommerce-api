package edu.neu.karanwadhwa.springecommerceapi.service;

import edu.neu.karanwadhwa.springecommerceapi.model.Product;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
import edu.neu.karanwadhwa.springecommerceapi.repository.ProductRepository;
import edu.neu.karanwadhwa.springecommerceapi.repository.UserRepository;
import edu.neu.karanwadhwa.springecommerceapi.validation.UserAuthenticationException;
import edu.neu.karanwadhwa.springecommerceapi.validation.UserNotAllowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<User> createProduct(int userid, Product product){
        User seller = userRepository.findById(userid).orElse(null);
        if(seller == null) throw new UserAuthenticationException("Seller Not found!");
        if(!seller.getUsertype().equals("seller") || !seller.getUsertype().equals("admin"))
            throw new UserNotAllowedException(seller.getUsertype());
        Collection<Product> inventory = seller.getInventory();
        inventory.add(product);
        seller.setInventory(inventory);
        return new ResponseEntity<>(userRepository.save(seller), HttpStatus.CREATED);
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
