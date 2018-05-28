package udemy.spring.hibernateDemo.instructorCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.*;

public class ReadInstructorDemo {
    public static void main( String[] args ) {
        System.out.println("Hello World! Reading instructor(s)");
        int instructorId = 2;

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory = new Configuration()
                .configure("hb_01_one_to_one_uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // Student object
        Instructor theInstructor;

        // Get a session from factory
        Session session = factory.openSession();

        // Try and interact with the table.  Get the student by id
        try {
            session.beginTransaction();
            theInstructor = session.get(Instructor.class, instructorId);
            session.getTransaction().commit();
            System.out.println("Instructor : " + theInstructor.toString());
            System.out.println("Courses    : " + theInstructor.getCourses().toString());
            System.out.println("retrieved?");
        } finally {
            session.close();
            factory.close();
        }

        System.out.printf(theInstructor.toString());
    }
}
