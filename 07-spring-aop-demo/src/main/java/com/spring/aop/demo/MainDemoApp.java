package com.spring.aop.demo;

import com.spring.aop.demo.config.DemoConfig;
import com.spring.aop.demo.dao.AccountDAO;
import com.spring.aop.demo.dao.MembershipDAO;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountBean = context.getBean("accountDAO", AccountDAO.class);

        MembershipDAO membershipBean = context.getBean("membershipDAO", MembershipDAO.class);

        accountBean.addAccount();

        membershipBean.addAccount();

        context.close();
    }
}
