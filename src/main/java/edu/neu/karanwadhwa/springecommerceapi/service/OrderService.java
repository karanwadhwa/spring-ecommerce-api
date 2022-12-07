package edu.neu.karanwadhwa.springecommerceapi.service;

import edu.neu.karanwadhwa.springecommerceapi.dao.OrderDAO;
import edu.neu.karanwadhwa.springecommerceapi.dao.ProductDAO;
import edu.neu.karanwadhwa.springecommerceapi.dao.UserDAO;
import edu.neu.karanwadhwa.springecommerceapi.dao.impl.OrderDAOImpl;
import edu.neu.karanwadhwa.springecommerceapi.dao.impl.ProductDAOImpl;
import edu.neu.karanwadhwa.springecommerceapi.dao.impl.UserDAOImpl;
import edu.neu.karanwadhwa.springecommerceapi.model.Order;
import edu.neu.karanwadhwa.springecommerceapi.model.OrderItem;
import edu.neu.karanwadhwa.springecommerceapi.model.Product;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
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
    private final UserDAO userDAO = new UserDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();

    public Order getOrderById(int orderId){
        return orderDAO.findById(orderId);
    }

    public ResponseEntity<Order> createOrder(int userid, Order order){
        User user = userDAO.findById(userid);
        if(user == null) throw new UserAuthenticationException("User Not found!");
        if(!user.getUsertype().equals("customer"))
            throw new UserNotAllowedException(user.getUsertype());
        Collection<OrderItem> items = order.getItems();
        List<Product> products = new ArrayList<>();
        for (OrderItem item : items){
            Product product = productDAO.findById(item.getProductId());
            if(!isNull(product)) {
                product.setQuantity(product.getQuantity() - item.getQuantity());
                products.add(product);
            }
        }
        productDAO.saveAll(products);
        return new ResponseEntity<>(userDAO.createOrder(userid, order), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Order>> findOrders(int userid){
        return new ResponseEntity<>(orderDAO.findByUserId(userid), HttpStatus.OK);
    }
}
