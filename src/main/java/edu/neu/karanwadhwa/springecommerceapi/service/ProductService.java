package edu.neu.karanwadhwa.springecommerceapi.service;

import edu.neu.karanwadhwa.springecommerceapi.dao.ProductDAO;
import edu.neu.karanwadhwa.springecommerceapi.dao.UserDAO;
import edu.neu.karanwadhwa.springecommerceapi.dao.impl.ProductDAOImpl;
import edu.neu.karanwadhwa.springecommerceapi.dao.impl.UserDAOImpl;
import edu.neu.karanwadhwa.springecommerceapi.model.Product;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
import edu.neu.karanwadhwa.springecommerceapi.validation.UserAuthenticationException;
import edu.neu.karanwadhwa.springecommerceapi.validation.UserNotAllowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final UserDAO userDAO = new UserDAOImpl();

    public ResponseEntity<Product> createProduct(Product product){
        int sellerId = product.getSeller();
        User seller = userDAO.findById(sellerId);
        if(seller == null) throw new UserAuthenticationException("Seller Not found!");
        if(!seller.getUsertype().equals("seller"))
            throw new UserNotAllowedException(seller.getUsertype());
        return new ResponseEntity<>(productDAO.create(product), HttpStatus.CREATED);
    }

    public List<Product> getProducts(){
        return productDAO.getAll();
    }

    public Product getProductById(int id){
        return productDAO.findById(id);
    }

    public List<Product> getProductByName(String name){
        return productDAO.findByName(name);
    }

    public Product updateProduct(Product newItem){
        Product product = productDAO.findById(newItem.getId());
        product.setName(newItem.getName());
        product.setPrice(newItem.getPrice());
        product.setQuantity(newItem.getQuantity());
        product.setThumbnail_url(newItem.getThumbnail_url());
        product.setDescription(newItem.getDescription());
        return productDAO.update(product);
    }

    public String deleteProductById(int id){
        productDAO.delete(id);
        return "Product deleted: #"+id;
    }

    public List<Product> getProductBySellerId(int sellerId) {
        return productDAO.findBySellerId(sellerId);
    }
}
