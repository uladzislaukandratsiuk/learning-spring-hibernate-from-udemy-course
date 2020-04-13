package com.web.customer.tracker.dao;

import com.web.customer.tracker.entity.User;

public interface UserDao {

    User findUserByName(String theUserName);

    void save(User user);
}
