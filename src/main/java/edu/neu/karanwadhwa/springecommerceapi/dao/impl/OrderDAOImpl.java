package edu.neu.karanwadhwa.springecommerceapi.dao.impl;

import edu.neu.karanwadhwa.springecommerceapi.dao.OrderDAO;
import edu.neu.karanwadhwa.springecommerceapi.model.Order;
import org.hibernate.Query;
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
        Query query = session.createQuery("FROM Order o left join fetch o.user where o.orderId = :orderidParam");
        query.setParameter("orderidParam", id);
        Order order = (Order) query.uniqueResult();

        session.getTransaction().commit();
        session.close();

        return order;
    }

    @Override
    public List<Order> getAll(int id) {
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

    @Override
    public List<Order> findByUserId(int userid) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Order o left join fetch o.user where o.user.id = :useridParam");
        query.setParameter("useridParam", userid);
        List<Order> orders = (List<Order>) query.list();

        session.getTransaction().commit();
        session.close();
        return orders;
    }
}
