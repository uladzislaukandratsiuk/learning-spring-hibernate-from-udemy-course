package com.hibernate.advanced.mappings.demo;

import com.hibernate.advanced.mappings.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetCoursesForStudentDemo {

    private static Logger log = LoggerFactory.getLogger(GetCoursesForStudentDemo.class);

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (sessionFactory) {

            Session session = sessionFactory.getCurrentSession();

            log.info("Starting transaction!");

            session.beginTransaction();

            int id = 1;
            Student student = session.get(Student.class, id);

            if (student != null) {

                log.info("Found student={}, with id={}", student, id);

                log.info("Found courses for student={}", student.getCourses());

            } else {
                log.info("Student with id={}, is not found!", id);
            }

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
