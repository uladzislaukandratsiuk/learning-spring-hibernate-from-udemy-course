package com.spring.aop.demo;

import com.spring.aop.demo.config.DemoConfig;
import com.spring.aop.demo.copydao.CopyAccountDAO;
import com.spring.aop.demo.pojo.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterFinallyDemoApp {

    private static Logger log = LoggerFactory.getLogger(AfterFinallyDemoApp.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        CopyAccountDAO accountBean = context.getBean("copyAccountDAO", CopyAccountDAO.class);

        List<Account> accounts = null;

        try {
            accounts = accountBean.findAccounts(false);
        } catch (Exception exc) {
            log.info("Catching exception:{}", exc.toString());
        }

        log.info("Main app return accounts:{}", accounts);

        context.close();
    }
}
