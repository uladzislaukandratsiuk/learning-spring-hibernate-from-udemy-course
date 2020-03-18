package com.hibernate.advanced.mappings.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

    private final static Logger log = LoggerFactory.getLogger(TestJDBC.class);

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String pass = "hbstudent";

        try {
            log.info("Connecting to database: {}", jdbcUrl);

            Connection myConn =
                    DriverManager.getConnection(jdbcUrl, user, pass);

            log.info("Connection to url {} successful!!!", myConn.getMetaData().getURL());

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
