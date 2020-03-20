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

public class CreateCoursesAndReviewsDemo {

    private static Logger log = LoggerFactory.getLogger(CreateCoursesAndReviewsDemo.class);

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

            Course course = new Course("Java 8 - Crush Course");

            course.addReview(new Review("Great course love it"));
            course.addReview(new Review("Cool course, well done"));
            course.addReview(new Review("Such a waste"));

            log.info("Saving course...");

            session.save(course);


            session.getTransaction().commit();

            log.info("Transaction committed!");
        }
    }
}
