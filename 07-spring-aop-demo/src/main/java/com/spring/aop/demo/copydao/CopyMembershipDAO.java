package com.spring.aop.demo.copydao;

import org.springframework.stereotype.Component;

@Component
public class CopyMembershipDAO {

    public boolean copyAddMembershipAccount() {
        System.out.println(getClass() +
                ".copyAddMembershipAccount()" +
                ": Adding a membership account");
        return true;
    }
}
