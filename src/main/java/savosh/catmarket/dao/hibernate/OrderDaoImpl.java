package savosh.catmarket.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import savosh.catmarket.dao.OrderDao;
import savosh.catmarket.model.Account;
import savosh.catmarket.model.Order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderDaoImpl implements OrderDao {
    private SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order create(Order object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public Set<Order> readAll() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Order> list = session.createCriteria(Order.class).list();
        session.getTransaction().commit();
        return new HashSet<>(list);
    }

    @Override
    public Order read(Order emptyObjectWithPk) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Order order = (Order) session.get(Order.class, emptyObjectWithPk.getId());
        session.getTransaction().commit();
        return order;
    }

    @Override
    public void update(Order object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Order objectWithPk) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(objectWithPk);
        session.getTransaction().commit();
    }

    @Override
    public Order createWithOrderProducts(Order order) {
        return create(order);
    }

    @Override
    public Set<Order> readAllWithOrderProducts() {
        return readAll();
    }
}
