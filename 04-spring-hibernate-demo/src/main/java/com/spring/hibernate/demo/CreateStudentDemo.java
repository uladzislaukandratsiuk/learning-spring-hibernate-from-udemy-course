package com.spring.hibernate.demo;

import com.spring.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateStudentDemo {

    private static Logger log = LoggerFactory.getLogger(CreateStudentDemo.class);

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (sessionFactory) {

            Session session = sessionFactory.getCurrentSession();

            Student student = new Student("Vlad", "Kondratuk", "vladkondr@mail.com");

            log.info("Creating new student object: {}", student);

            session.beginTransaction();

            log.info("Saving student...");

            session.save(student);

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
