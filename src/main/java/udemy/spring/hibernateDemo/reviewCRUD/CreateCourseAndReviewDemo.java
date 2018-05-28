package udemy.spring.hibernateDemo.reviewCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Course;
import udemy.spring.hibernateDemo.entity.Instructor;
import udemy.spring.hibernateDemo.entity.InstructorDetail;
import udemy.spring.hibernateDemo.entity.Review;


//
// Look at creating a course, complete with reviews and saving the lot
//
public class CreateCourseAndReviewDemo {
    public static void main(String[] args) {

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory = new Configuration()
                .configure("hb_01_one_to_one_uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)        // need factory to know about BOTH classes
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)            // added review, so need to link it in. 28/5/18
                .buildSessionFactory();

        // Get a session from factory
        Session session = factory.openSession();

        // If we set up course->reviews as
        Course course=new Course("created with mappedBy 2");
        course.add(new Review("review 1"));
        course.add(new Review("review 2"));
        course.add(new Review("review 3"));


        // Try and interact with the table.  Get the student by id
        try {

            // Save the course (with reviews, we hope)
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }







    }
}
