package edu.neu.karanwadhwa.springecommerceapi.dao.impl;

import edu.neu.karanwadhwa.springecommerceapi.dao.ProductDAO;
import edu.neu.karanwadhwa.springecommerceapi.model.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Product findById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);

        session.getTransaction().commit();
        session.close();
        return product;
    }

    @Override
    public List<Product> findByName(String keyword) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.ilike("name","%"+keyword+"%"));
        List<Product> products = (List<Product>) criteria.list();

        session.getTransaction().commit();
        session.close();
        return products;
    }

    @Override
    public List<Product> findBySellerId(int sellerId){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("seller",sellerId));
        List<Product> products = (List<Product>) criteria.list();

        session.getTransaction().commit();
        session.close();
        return products;
    }

    @Override
    public List<Product> getAll(int userid) {
        return null;
    }

    public List<Product> getAll(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        List<Product> products = criteria.list();

        session.getTransaction().commit();
        session.close();
        return products;
    }

    @Override
    public Product create(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(product);

        session.getTransaction().commit();
        session.close();
        return product;
    }

    @Override
    public Product update(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(product);

        session.getTransaction().commit();
        session.close();
        return product;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.delete(product);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveAll(List<Product> products) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for(Product product : products)
            session.saveOrUpdate(product);

        session.getTransaction().commit();
        session.close();
    }
}
