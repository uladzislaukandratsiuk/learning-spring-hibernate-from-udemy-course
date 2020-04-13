package com.web.customer.tracker.dao;

import com.web.customer.tracker.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserByName(String theUserName) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<User> query = currentSession.createQuery("from User where userName=:userName", User.class);
        query.setParameter("userName", theUserName);

        User user;

        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }

        return user;
    }

    @Override
    public void save(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);
    }
}
