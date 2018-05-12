package udemy.spring.hibernateDemo.studentCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Student;

/**
 * Hello world!
 *
 */
public class ReadStudentDemo
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World! Reading" );
        int studentId=7;

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory=new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class)
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

        System.out.printf(theStudent.toString());

    }


}
