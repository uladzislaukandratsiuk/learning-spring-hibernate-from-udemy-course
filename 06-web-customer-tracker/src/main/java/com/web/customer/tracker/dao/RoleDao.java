package com.web.customer.tracker.dao;

import com.web.customer.tracker.entity.Role;

public interface RoleDao {

    Role findRoleByName(String theRoleName);
}
