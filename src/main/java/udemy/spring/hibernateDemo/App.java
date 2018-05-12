package udemy.spring.hibernateDemo;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Student;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!  Doing instructor" );

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
//            session.save(theInstructor);
            session.getTransaction().commit();

            System.out.println("saved?");
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            factory.close();
        }

    }
}
