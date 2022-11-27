package edu.neu.karanwadhwa.springecommerceapi.controller;

import edu.neu.karanwadhwa.springecommerceapi.model.Order;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
import edu.neu.karanwadhwa.springecommerceapi.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/{orderid}")
    public Order getOrderById(@PathVariable int orderid){
        return orderService.getOrderById(orderid);
    }

    @PutMapping("/user/{userid}/order/create")
    public ResponseEntity<User> createOrder(@PathVariable int userid, @RequestBody Order order){
        return orderService.createOrder(userid, order);
    }
}
