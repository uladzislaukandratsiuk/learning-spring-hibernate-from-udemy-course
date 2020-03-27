package com.spring.aop.demo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    public boolean addMembershipAccount() {
        System.out.println(getClass() +
                ".addMembershipAccount()" +
                ": Adding a membership account");
        return true;
    }
}
