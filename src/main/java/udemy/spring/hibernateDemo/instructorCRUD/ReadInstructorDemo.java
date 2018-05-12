package udemy.spring.hibernateDemo.instructorCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Instructor;
import udemy.spring.hibernateDemo.entity.InstructorDetail;
import udemy.spring.hibernateDemo.entity.Student;

public class ReadInstructorDemo {
    public static void main( String[] args ) {
        System.out.println("Hello World! Reading instructor(s)");
        int instructorId = 2;

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
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
            System.out.println("retrieved?");
        } finally {
            factory.close();
        }

        System.out.printf(theInstructor.toString());
    }
}
