package com.hibernate.advanced.mappings.demo;

import com.hibernate.advanced.mappings.demo.entity.Course;
import com.hibernate.advanced.mappings.demo.entity.InstructorEagerFetch;
import com.hibernate.advanced.mappings.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EagerFetchDemo {

    private static Logger log = LoggerFactory.getLogger(EagerFetchDemo.class);

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(InstructorEagerFetch.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        try (sessionFactory) {

            Session session = sessionFactory.getCurrentSession();

            log.info("Starting transaction!");

            session.beginTransaction();

            int id = 1;
            InstructorEagerFetch instructorEagerFetch = session.get(InstructorEagerFetch.class, id);

            if (instructorEagerFetch != null) {

                log.info("Found instructor: {}", instructorEagerFetch);

                log.info("Found courses: {}", instructorEagerFetch.getCourses());

            } else {
                log.info("Instructor with id={}, is not found!", id);
            }

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
