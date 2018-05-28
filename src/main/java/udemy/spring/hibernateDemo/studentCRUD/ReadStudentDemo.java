package udemy.spring.hibernateDemo.studentCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.*;

/**
 * Hello world!
 *
 */
public class ReadStudentDemo
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World! Reading" );
        int studentId=1;

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory=new Configuration()
                .configure("hb_01_one_to_one_uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)        // need factory to know about BOTH classes
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)            // added review, so need to link it in. 28/5/18
                .addAnnotatedClass(Student.class)            // added review, so need to link it in. 28/5/18
                .buildSessionFactory();

        // Student object
        Student theStudent;

        // Get a session from factory
        Session session=factory.openSession();

        // Try and interact with the table.  Get the student by id
        try {
            session.beginTransaction();
            theStudent=session.get(Student.class,studentId);
            session.getTransaction().commit();
            System.out.println("retrieved?");
        } finally {
            factory.close();
        }

        session.close();
        System.out.printf(theStudent.toString());

    }


}
