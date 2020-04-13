package com.web.customer.tracker.service;

import com.web.customer.tracker.entity.User;
import com.web.customer.tracker.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findUserByName(String userName);

    void save(CrmUser crmUser);
}
