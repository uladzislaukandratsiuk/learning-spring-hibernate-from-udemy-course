package com.hibernate.advanced.mappings.demo;

import com.hibernate.advanced.mappings.demo.entity.Course;
import com.hibernate.advanced.mappings.demo.entity.Instructor;
import com.hibernate.advanced.mappings.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateCoursesDemo {

    private static Logger log = LoggerFactory.getLogger(CreateCoursesDemo.class);

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        try (sessionFactory) {

            Session session = sessionFactory.getCurrentSession();

            log.info("Starting transaction!");

            session.beginTransaction();

            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            if (instructor != null) {

                Course firstCourse = new Course("Java 8 - Crush Course");
                Course secondCourse = new Course("Java - Lambda and Streams full Course");

                instructor.addCourse(firstCourse);
                instructor.addCourse(secondCourse);

                log.info("Saving courses...");

                session.save(firstCourse);
                session.save(secondCourse);

            } else {
                log.info("Instructor with id={}, is not found!", id);
            }

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
