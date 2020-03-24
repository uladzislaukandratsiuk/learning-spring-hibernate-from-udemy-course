package com.web.customer.tracker.dao;

import com.web.customer.tracker.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        Session session = sessionFactory.getCurrentSession();

        Query<Customer> customersQuery =
                session.createQuery("from Customer order by lastName", Customer.class);

        return customersQuery.getResultList();
    }

    @Override
    public void saveCustomer(Customer customer) {

        Session session = sessionFactory.getCurrentSession();

        if (customer != null) {
            session.saveOrUpdate(customer);
        }
    }

    @Override
    public Customer getCustomer(int id) {

        Session session = sessionFactory.getCurrentSession();

        return session.get(Customer.class, id);
    }

    @Override
    public void deleteCustomer(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query customersQuery =
                session.createQuery("delete from Customer where id=:customerId");

        customersQuery.setParameter("customerId", id);

        customersQuery.executeUpdate();
    }
}
