package com.spring.aop.demo.copydao;

import com.spring.aop.demo.pojo.Account;
import org.springframework.stereotype.Component;

@Component
public class CopyAccountDAO {

    public void copyAddAccount(Account account) {
        System.out.println(getClass() +
                ".copyAddAccount(Account account)" +
                ": Adding an account");
    }

    public void copyAddVipAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() +
                ".copyAddVipAccount(Account account, boolean vipFlag)" +
                ": Adding an vip account");
    }
}
