package com.spring.hibernate.demo;

import com.spring.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateStudentDemo {

    private static Logger log = LoggerFactory.getLogger(UpdateStudentDemo.class);

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (sessionFactory) {

            int studentId = 1;

            Session session = sessionFactory.getCurrentSession();

            log.info("Start transaction!");

            session.beginTransaction();

            Student student = session.get(Student.class, studentId);

            log.info("Get student by id={} : {}", studentId, student);

            log.info("Updating student first name...");

            student.setFirstName("UpdatedName");

            session.getTransaction().commit();

            log.info("Transaction committed!");

            session = sessionFactory.getCurrentSession();

            log.info("Start transaction!");

            session.beginTransaction();

            log.info("Updating students emails...");

            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
