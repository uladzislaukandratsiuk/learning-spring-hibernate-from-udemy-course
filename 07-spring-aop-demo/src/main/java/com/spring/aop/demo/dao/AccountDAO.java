package com.spring.aop.demo.dao;

import com.spring.aop.demo.pojo.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

    public void addAccount(Account account) {
        System.out.println(getClass() + ": Adding an account");
    }

    public void addVipAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + ": Adding an vip account");
    }
}
