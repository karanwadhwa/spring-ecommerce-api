package edu.neu.karanwadhwa.springecommerceapi.service;

import edu.neu.karanwadhwa.springecommerceapi.model.Order;
import edu.neu.karanwadhwa.springecommerceapi.model.OrderItem;
import edu.neu.karanwadhwa.springecommerceapi.model.Product;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
import edu.neu.karanwadhwa.springecommerceapi.repository.OrderRepository;
import edu.neu.karanwadhwa.springecommerceapi.repository.ProductRepository;
import edu.neu.karanwadhwa.springecommerceapi.repository.UserRepository;
import edu.neu.karanwadhwa.springecommerceapi.validation.UserAuthenticationException;
import edu.neu.karanwadhwa.springecommerceapi.validation.UserNotAllowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Order getOrderById(int orderId){
        return orderRepository.findById(orderId).orElse(null);
    }

    public ResponseEntity<User> createOrder(int userid, Order order){
        User user = userRepository.findById(userid).orElse(null);
        if(user == null) throw new UserAuthenticationException("User Not found!");
        if(!user.getUsertype().equals("customer"))
            throw new UserNotAllowedException(user.getUsertype());
        user.addToOrders(order);
        Collection<OrderItem> items = order.getItems();
        List<Product> products = new ArrayList<>();
        for (OrderItem item : items){
            Product product = productRepository.findById(item.getProductId()).orElse(null);
            if(!isNull(product)) {
                product.setQuantity(product.getQuantity() - item.getQuantity());
                products.add(product);
            }
        };
        productRepository.saveAll(products);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
}
