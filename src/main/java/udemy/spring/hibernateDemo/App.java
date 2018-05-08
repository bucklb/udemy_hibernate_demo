package udemy.spring.hibernateDemo;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Student theStudent=new Student("zachary","zed","z@zee.com");

        SessionFactory factory=new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
                                .buildSessionFactory();

        Session session=factory.openSession();

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
