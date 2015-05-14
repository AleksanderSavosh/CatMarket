package savosh.catmarket.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import savosh.catmarket.dao.AccountDao;
import savosh.catmarket.model.Account;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountDaoImpl implements AccountDao {
    private SessionFactory sessionFactory;

    public AccountDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Account create(Account object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public Set<Account> readAll() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Account> list = session.createCriteria(Account.class).list();
        session.getTransaction().commit();
        return new HashSet<>(list);
    }

    @Override
    public Account read(Account emptyObjectWithPk) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Account account = (Account) session.get(Account.class, emptyObjectWithPk.getLogin());
        session.getTransaction().commit();
        return account;
    }

    @Override
    public void update(Account object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Account objectWithPk) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(objectWithPk);
        session.getTransaction().commit();
    }
}
