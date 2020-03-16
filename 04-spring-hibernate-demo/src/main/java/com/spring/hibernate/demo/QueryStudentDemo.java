package com.spring.hibernate.demo;

import com.spring.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.TypedQuery;
import java.util.List;

public class QueryStudentDemo {

    private static Logger log = LoggerFactory.getLogger(QueryStudentDemo.class);

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (sessionFactory) {

            Session session = sessionFactory.getCurrentSession();

            log.info("Start transaction!");

            session.beginTransaction();

            TypedQuery<Student> query = session.createQuery("from Student", Student.class);
            List<Student> students = query.getResultList();

            log.info("All students:");
            displayStudents(students);

            query = session.createQuery("from Student s where s.lastName='Doe'", Student.class);
            students = query.getResultList();

            log.info("Students with last name 'Doe':");
            displayStudents(students);

            query = session.createQuery("from Student s where" +
                    " s.lastName='Doe' or s.firstName='Daffy'", Student.class);
            students = query.getResultList();

            log.info("Students with last name 'Doe' or first name 'Daffy':");
            displayStudents(students);

            query = session.createQuery("from Student s where" +
                    " s.email like '%email.com'", Student.class);
            students = query.getResultList();

            log.info("Students who email ends with email.com:");
            displayStudents(students);

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }

    private static void displayStudents(List<Student> students) {
        for (Student student: students) {
            log.info("{}", student);
        }
    }
}
