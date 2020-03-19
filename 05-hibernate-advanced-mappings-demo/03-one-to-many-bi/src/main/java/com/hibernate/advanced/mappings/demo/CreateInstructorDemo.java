package com.hibernate.advanced.mappings.demo;

import com.hibernate.advanced.mappings.demo.entity.Instructor;
import com.hibernate.advanced.mappings.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateInstructorDemo {

    private static Logger log = LoggerFactory.getLogger(CreateInstructorDemo.class);

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        try (sessionFactory) {

            Session session = sessionFactory.getCurrentSession();

            Instructor instructor =
                    new Instructor("Chad", "Darby", "darby@luv2code.com");

            InstructorDetail instructorDetail =
                    new InstructorDetail(
                            "http://www.luv2code.com/youtube",
                            "Luv 2 code");

            instructor.setInstructorDetail(instructorDetail);

            log.info("Starting transaction!");

            session.beginTransaction();

            log.info("Saving instructor and instructorDetail(because of CascadeType.ALL)...");

            session.save(instructor);

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
