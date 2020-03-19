package com.hibernate.advanced.mappings.demo;

import com.hibernate.advanced.mappings.demo.entity.Course;
import com.hibernate.advanced.mappings.demo.entity.Instructor;
import com.hibernate.advanced.mappings.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FetchJoinDemo {

    private static Logger log = LoggerFactory.getLogger(FetchJoinDemo.class);

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

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i " +
                                    "JOIN FETCH i.courses " +
                                    "where i.id=:instructorId",
                            Instructor.class);

            query.setParameter("instructorId", id);

            Instructor instructor = query.getSingleResult();

            if (instructor != null) {
                log.info("Found instructor: {}", instructor);
            } else {
                log.info("Instructor with id={}, is not found!", id);
            }

            session.getTransaction().commit();

            log.info("Transaction committed!");

            session.close();

            log.info("Session closed!");

            if (instructor != null) {
                log.info("Found courses: {}", instructor.getCourses());
            } else {
                log.info("Instructor is not found! Can't get the courses!");
            }
        }
    }
}
