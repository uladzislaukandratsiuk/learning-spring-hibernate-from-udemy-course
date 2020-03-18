package com.hibernate.advanced.mappings.demo;

import com.hibernate.advanced.mappings.demo.entity.Instructor;
import com.hibernate.advanced.mappings.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetInstructorDetailDemo {

    private static Logger log = LoggerFactory.getLogger(GetInstructorDetailDemo.class);

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

            int id = 1;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            log.info("Found instructorDetail: {}", instructorDetail);

            log.info("Found associated instructor: {}", instructorDetail.getInstructor());

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
