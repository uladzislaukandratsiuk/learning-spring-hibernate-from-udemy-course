package com.spring.aop.demo;

import com.spring.aop.demo.config.DemoConfig;
import com.spring.aop.demo.dao.AccountDAO;
import com.spring.aop.demo.dao.MembershipDAO;
import com.spring.aop.demo.pojo.Account;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountBean = context.getBean("accountDAO", AccountDAO.class);

        MembershipDAO membershipBean = context.getBean("membershipDAO", MembershipDAO.class);

        Account account = new Account();

        accountBean.addAccount(account);

        accountBean.addVipAccount(account, true);

        membershipBean.addMembershipAccount();

        context.close();
    }
}
