package udemy.spring.hibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Student;

/**
 * Hello world!
 *
 */
public class WriteStudentDemo
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // Create a student object
        Student theStudent=new Student("zachary","zed","z@zee.com");

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory=new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();

        // Get a session from factory
        Session session=factory.openSession();

        // Try and interact with the table
        try {
            session.beginTransaction();
            session.save(theStudent);
            session.getTransaction().commit();
            System.out.println("saved?");
        } finally {
            factory.close();
        }

    }
}
