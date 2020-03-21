package com.hibernate.advanced.mappings.demo;

import com.hibernate.advanced.mappings.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateCourseAndStudentDemo {

    private static Logger log = LoggerFactory.getLogger(CreateCourseAndStudentDemo.class);

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

            Course course = new Course("Java 8 - Crush Course");

            log.info("Saving course...");

            session.save(course);

            log.info("Course: {} is saved!", course);

            Student firstStudent =
                    new Student("Vlad", "Kondratuk", "vladkondr@mail.com");
            Student secondStudent =
                    new Student("John", "Doe", "john@mail.com");

            course.addStudent(firstStudent);
            course.addStudent(secondStudent);

            log.info("Saving students...");

            session.save(firstStudent);
            session.save(secondStudent);

            log.info("Students: {} is saved!", course.getStudents());

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
