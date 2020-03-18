package com.hibernate.advanced.mappings.demo;

import com.hibernate.advanced.mappings.demo.entity.Instructor;
import com.hibernate.advanced.mappings.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteInstructorDetailDemo {

    private static Logger log = LoggerFactory.getLogger(DeleteInstructorDetailDemo.class);

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

            int id = 3;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            if (instructorDetail != null) {

                log.info("Found instructorDetail: {}", instructorDetail);

                log.info("Found associated instructor: {}", instructorDetail.getInstructor());

                log.info("Setting instructor field reference instructorDetail to null");

                instructorDetail.getInstructor().setInstructorDetail(null);

                log.info("Deleting instructorDetail...");

                session.delete(instructorDetail);
            }

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
