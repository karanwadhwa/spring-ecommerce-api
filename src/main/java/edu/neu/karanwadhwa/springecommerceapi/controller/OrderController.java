package edu.neu.karanwadhwa.springecommerceapi.controller;

import edu.neu.karanwadhwa.springecommerceapi.model.EmailDetails;
import edu.neu.karanwadhwa.springecommerceapi.model.Order;
import edu.neu.karanwadhwa.springecommerceapi.model.OrderItem;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
import edu.neu.karanwadhwa.springecommerceapi.service.EmailService;
import edu.neu.karanwadhwa.springecommerceapi.service.InvoiceGenerator;
import edu.neu.karanwadhwa.springecommerceapi.service.OrderService;
import edu.neu.karanwadhwa.springecommerceapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final EmailService emailService;

    public OrderController(OrderService orderService, UserService userService, EmailService emailService) {
        this.orderService = orderService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/order/{orderid}")
    public Order getOrderById(@PathVariable int orderid){
        return orderService.getOrderById(orderid);
    }

    @PutMapping("/user/{userid}/order/create")
    public ResponseEntity<Order> createOrder(@PathVariable int userid, @RequestBody Order order){
        User user = userService.getUserById(userid);
        Order createdOrder = orderService.createOrder(userid, order);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(user.getEmail());
        emailDetails.setSubject("Order Confirmation: CSYE 6220 ecommerce project");
        StringBuilder messageBody = new StringBuilder();
        messageBody.append("Dear ").append(user.getFname()).append(",\n\n");
        messageBody.append("Thank you for placing your order\n\n");
        messageBody.append("Order Details:");
        messageBody.append("\nStatus: ").append(createdOrder.getStatus());
        messageBody.append("\nShipping Address: ").append(createdOrder.getAddress().toString());
        messageBody.append("\nProducts in your order: ");
        for(OrderItem item : createdOrder.getItems())
            messageBody.append("\n").append(item.getName()).append(",");
        messageBody.append("\nOrder Total: ").append(createdOrder.getOrderTotal());
        messageBody.append("\n\nDownload invoice here: ").append("http://localhost:8080/order/invoice/").append(createdOrder.getOrderId());

        emailDetails.setMsgBody(messageBody.toString());
        emailService.sendSimpleEmail(emailDetails);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
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
