package com.spring.aop.demo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    public void addMembershipAccount() {
        System.out.println(getClass() + ": Adding a membership account");
    }
}
