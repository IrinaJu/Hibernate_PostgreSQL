package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }
    @Override
    public void createUsersTable() {

        String sql = "create table if not exists users (" +
                "id SERIAL PRIMARY KEY," +
                "name varchar(50) not null," +
                "lastName varchar(50)," +
                "age smallint)";
        Transaction transaction = null;
        try { Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {


        String sql = "drop table if exists users";

        Transaction transaction = null;
        try { Session session =  Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        User user = new User(name, lastName, age);

        Transaction transaction = null;

        try { Session session =  Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

        try { Session session =  Util.getSessionFactory().openSession();
            session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> list = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from User");
            list = query.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void cleanUsersTable() {

        try { Session session =  Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}