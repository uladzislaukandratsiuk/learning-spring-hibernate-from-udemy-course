package com.spring.hibernate.demo;

import com.spring.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteStudentDemo {

    private static Logger log = LoggerFactory.getLogger(DeleteStudentDemo.class);

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (sessionFactory) {

            int firstStudentId = 1;
            int secondStudentId = 2;

            Session session = sessionFactory.getCurrentSession();

            log.info("Start transaction!");

            session.beginTransaction();

            Student student = session.get(Student.class, firstStudentId);

            log.info("Deleting student with id=1...\n {}", student);

            session.delete(student);

            log.info("Deleting student with id=2...\n {}", session.get(Student.class, secondStudentId));

            session.createQuery("delete from Student where id=2").executeUpdate();

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
