package edu.neu.karanwadhwa.springecommerceapi.controller;

import edu.neu.karanwadhwa.springecommerceapi.model.Order;
import edu.neu.karanwadhwa.springecommerceapi.service.InvoiceGenerator;
import edu.neu.karanwadhwa.springecommerceapi.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<Order> createOrder(@PathVariable int userid, @RequestBody Order order){
        return orderService.createOrder(userid, order);
    }

    @GetMapping("/user/{userid}/orders")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable int userid){
        return orderService.findOrders(userid);
    }

    @GetMapping("/order/invoice/{orderId}")
    public View getInvoice(@PathVariable int orderId){
        Order order = orderService.getOrderById(orderId);

        return new InvoiceGenerator(order);
    }
}
