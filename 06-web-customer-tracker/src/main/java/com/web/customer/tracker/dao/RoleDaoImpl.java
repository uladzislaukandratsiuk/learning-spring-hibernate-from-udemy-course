package com.web.customer.tracker.dao;

import com.web.customer.tracker.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    @Qualifier(value = "securitySessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Role findRoleByName(String theRoleName) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Role> query = currentSession.createQuery("from Role where name=:roleName", Role.class);
        query.setParameter("roleName", theRoleName);

        Role role;

        try {
            role = query.getSingleResult();
        } catch (Exception e) {
            role = null;
        }

        return role;
    }
}
