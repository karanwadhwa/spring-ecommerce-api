package edu.neu.karanwadhwa.springecommerceapi.dao.impl;

import edu.neu.karanwadhwa.springecommerceapi.dao.UserDAO;
import edu.neu.karanwadhwa.springecommerceapi.model.Order;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public User create(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public User findById(int userid) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, userid);

        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public User findByEmail(String email) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria critera = session.createCriteria(User.class);
        Criterion emailMatch = Restrictions.eq("email", email);
        critera.add(emailMatch);

        User user = (User) critera.uniqueResult();

        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public List<User> getAll(int userid) {
        return null;
    }

    @Override
    public void delete(int userid) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, userid);
        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User update(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);

        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public Order createOrder(int userid, Order order) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, userid);
        user.getOrders().add(order);
        order.setUser(user);
        session.saveOrUpdate(user);

        session.getTransaction().commit();
        session.close();
        return order;
    }
}
