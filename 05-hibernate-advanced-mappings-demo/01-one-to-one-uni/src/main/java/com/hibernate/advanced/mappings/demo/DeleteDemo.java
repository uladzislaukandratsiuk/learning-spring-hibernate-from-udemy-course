package com.hibernate.advanced.mappings.demo;

import com.hibernate.advanced.mappings.demo.entity.Instructor;
import com.hibernate.advanced.mappings.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteDemo {

    private static Logger log = LoggerFactory.getLogger(DeleteDemo.class);

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        try (sessionFactory) {

            Session session = sessionFactory.getCurrentSession();

            log.info("Starting transaction!");

            session.beginTransaction();

            int id = 2;
            Instructor instructor = session.get(Instructor.class, id);

            log.info("Found instructor: {}", instructor);

            if (instructor != null) {

                log.info("Deleting instructor and instructorDetail(because of CascadeType.ALL) {}", instructor);

                session.delete(instructor);
            }

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
