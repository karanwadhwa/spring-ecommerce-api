package edu.neu.karanwadhwa.springecommerceapi.dao.impl;

import edu.neu.karanwadhwa.springecommerceapi.dao.UserDAO;
import edu.neu.karanwadhwa.springecommerceapi.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserDAOImpl implements UserDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public User createUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public User getUserById(int userid) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, userid);

        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public void deleteUser(int userid) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, userid);
        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User updateUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);

        session.getTransaction().commit();
        session.close();
        return user;
    }
}
