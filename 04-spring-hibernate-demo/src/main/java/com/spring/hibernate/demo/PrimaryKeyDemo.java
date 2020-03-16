package com.spring.hibernate.demo;

import com.spring.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimaryKeyDemo {

    private static Logger log = LoggerFactory.getLogger(PrimaryKeyDemo.class);

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (sessionFactory) {

            Session session = sessionFactory.getCurrentSession();

            Student firstStudent = new Student("John", "Doe", "john@email.com");
            Student secondStudent = new Student("Marry", "Public", "marry@email.com");
            Student thirdStudent = new Student("Paul", "Wall", "paul@email.com");

            log.info("Creating new students objects:\n {},\n {},\n {}",
                    firstStudent, secondStudent, thirdStudent);

            session.beginTransaction();

            log.info("Saving students...");

            session.save(firstStudent);
            session.save(secondStudent);
            session.save(thirdStudent);

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
