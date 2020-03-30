package com.spring.aop.demo.copydao;

import com.spring.aop.demo.pojo.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CopyAccountDAO {

    private String name;
    private String serviceCode;

    public List<Account> findAccounts() {

        List<Account> accounts = new ArrayList<>();

        Account firstAcc = new Account("John", "Silver");
        Account secondAcc = new Account("Madhu", "Platinum");
        Account thirdAcc = new Account("Luca", "Gold");

        accounts.add(firstAcc);
        accounts.add(secondAcc);
        accounts.add(thirdAcc);

        return accounts;
    }

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

    public String getName() {
        System.out.println(getClass() + ".getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ".setName(String name)");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ".getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ".setServiceCode(String serviceCode)");
        this.serviceCode = serviceCode;
    }
}
