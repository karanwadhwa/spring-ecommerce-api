package edu.neu.karanwadhwa.springecommerceapi.service;

import edu.neu.karanwadhwa.springecommerceapi.model.Order;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
import edu.neu.karanwadhwa.springecommerceapi.repository.OrderRepository;
import edu.neu.karanwadhwa.springecommerceapi.repository.UserRepository;
import edu.neu.karanwadhwa.springecommerceapi.validation.UserAuthenticationException;
import edu.neu.karanwadhwa.springecommerceapi.validation.UserNotAllowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
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
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
}
