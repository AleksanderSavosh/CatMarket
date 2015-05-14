package savosh.catmarket.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import savosh.catmarket.dao.ProductDao;
import savosh.catmarket.model.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDaoImpl implements ProductDao {
    private SessionFactory sessionFactory;

    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product create(Product object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public Set<Product> readAll() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Product> list = session.createCriteria(Product.class).list();
        session.getTransaction().commit();
        return new HashSet<>(list);
    }

    @Override
    public Product read(Product emptyObjectWithPk) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = (Product) session.get(Product.class, emptyObjectWithPk.getBread());
        session.getTransaction().commit();
        return product;
    }

    @Override
    public void update(Product object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Product objectWithPk) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(objectWithPk);
        session.getTransaction().commit();
    }
}
