package com.hibernate.advanced.mappings.demo;

import com.hibernate.advanced.mappings.demo.entity.Course;
import com.hibernate.advanced.mappings.demo.entity.Instructor;
import com.hibernate.advanced.mappings.demo.entity.InstructorDetail;
import com.hibernate.advanced.mappings.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetCourseAndReviewsDemo {

    private static Logger log = LoggerFactory.getLogger(GetCourseAndReviewsDemo.class);

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        try (sessionFactory) {

            Session session = sessionFactory.getCurrentSession();

            log.info("Starting transaction!");

            session.beginTransaction();

            int id = 10;
            Course course = session.get(Course.class, id);

            if (course != null) {

                log.info("Found course: {}", course);

                log.info("Found reviews: {}", course.getReviews());

            } else {
                log.info("Course with id={}, is not found!", id);
            }

            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
