package com.hibernate.advanced.mappings.demo;

import com.hibernate.advanced.mappings.demo.entity.Course;
import com.hibernate.advanced.mappings.demo.entity.Instructor;
import com.hibernate.advanced.mappings.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetInstructorCoursesDemo {

    private static Logger log = LoggerFactory.getLogger(GetInstructorCoursesDemo.class);

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

                log.info("Found courses: {}", instructor.getCourses());

            } else {
                log.info("Instructor with id={}, is not found!", id);
            }

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
