package com.rest.crud.api.dao;

import com.rest.crud.api.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        Session session = entityManager.unwrap(Session.class);

        Query<Employee> query
                = session.createQuery("SELECT e FROM Employee e", Employee.class);

        return query.getResultList();
    }

    @Override
    public Employee getEmployee(int id) {

        Session session = entityManager.unwrap(Session.class);

        return session.get(Employee.class, id);
    }

    @Override
    public void saveEmployee(Employee employee) {

        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(employee);
    }

    @Override
    public void deleteEmployee(int id) {

        Session session = entityManager.unwrap(Session.class);

        Query<Employee> query
                = session.createQuery("DELETE e FROM Employee WHERE id=:employeeId", Employee.class);
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
