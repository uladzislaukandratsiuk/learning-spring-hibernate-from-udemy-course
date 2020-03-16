package com.spring.hibernate.demo;

import com.spring.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadStudentDemo {

    private static Logger log = LoggerFactory.getLogger(ReadStudentDemo.class);

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (sessionFactory) {

            Session session = sessionFactory.getCurrentSession();

            //saving
            Student tempStudent = new Student("Daffy", "Duck", "daffy@email.com");

            log.info("Creating new student object: {}", tempStudent);

            session.beginTransaction();

            log.info("Saving student...");

            session.save(tempStudent);

            session.getTransaction().commit();

            log.info("Transaction committed!");

            //reading
            log.info("New reading transaction!");

            session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            log.info("Get student with generated id: {}", tempStudent.getId());

            Student myStudent = session.get(Student.class, tempStudent.getId());

            log.info("Reading student data from db: {}", myStudent);

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
