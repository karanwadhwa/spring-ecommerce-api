package edu.neu.karanwadhwa.springecommerceapi.dao.impl;

import edu.neu.karanwadhwa.springecommerceapi.dao.OrderDAO;
import edu.neu.karanwadhwa.springecommerceapi.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Order findById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Order order = session.get(Order.class, id);

        session.getTransaction().commit();
        session.close();

        return order;
    }

    @Override
    public List<Order> getAll(int sellerId) {
        return null;
    }

    @Override
    public Order create(Order order) {
        return null;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
