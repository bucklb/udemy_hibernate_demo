package udemy.spring.hibernateDemo.courseCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Course;
import udemy.spring.hibernateDemo.entity.Instructor;
import udemy.spring.hibernateDemo.entity.InstructorDetail;

public class DeleteCourseDemo {
    public static void main(String[] args) {
        int courseId = 10;

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory = new Configuration()
                .configure("hb_01_one_to_one_uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)        // need factory to know about BOTH classes
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // Get a session from factory
        Session session = factory.openSession();

        // Try and interact with the table.  Get the student by id
        try {

            // the writing
            session.beginTransaction();

            // Locate the course and zap it
            Course c=session.get(Course.class,courseId);
            session.delete(c);

            // make it so!
            session.getTransaction().commit();


        } finally {
            factory.close();
        }

    }
}
