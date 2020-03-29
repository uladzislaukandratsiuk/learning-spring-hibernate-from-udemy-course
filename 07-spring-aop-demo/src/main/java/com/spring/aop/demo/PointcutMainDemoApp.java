package com.spring.aop.demo;

import com.spring.aop.demo.config.DemoConfig;
import com.spring.aop.demo.copydao.CopyAccountDAO;
import com.spring.aop.demo.copydao.CopyMembershipDAO;
import com.spring.aop.demo.pojo.Account;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PointcutMainDemoApp {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        CopyAccountDAO accountBean = context.getBean("copyAccountDAO", CopyAccountDAO.class);

        CopyMembershipDAO membershipBean = context.getBean("copyMembershipDAO", CopyMembershipDAO.class);

        accountBean.setName("Account name");
        accountBean.setServiceCode("Service code");
        accountBean.getName();
        accountBean.getServiceCode();

        Account account = new Account();
        account.setName("John");
        account.setLevel("Gold");

        accountBean.copyAddAccount(account);

        accountBean.copyAddVipAccount(account, true);

        membershipBean.copyAddMembershipAccount();

        context.close();
    }
}
