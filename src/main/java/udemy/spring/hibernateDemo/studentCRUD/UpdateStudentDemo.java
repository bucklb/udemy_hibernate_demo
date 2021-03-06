package udemy.spring.hibernateDemo.studentCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Student;

import java.util.Date;

public class UpdateStudentDemo {
    public static void main( String[] args )
    {
        System.out.println( "Update a student!" );
        int studentId=5;

        // Create a student object
        Student theStudent=new Student("zachary","zed",/*new Date("12/12/2012"),*/"z@zee.com");

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory=new Configuration()
                .configure("hb_01_one_to_one_uni.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // Get a session from factory
        Session session=factory.openSession();

        // Try and interact with the table
        try {
            session.beginTransaction();
            if (1!=1) {
                // read a student in to object, manipulate object (and the commit should make it so
                Student myStudent=session.get(Student.class,studentId);
                myStudent.setLastName("Zebediah");
                myStudent.setEmail("zeb@diah.com");
            } else {
                // update using a query, rather than by first pulling in to an object
                session.createQuery("update Student set email='d@D.dom' where firstName='Dave'").executeUpdate();
            }
            session.getTransaction().commit();
            System.out.println("updated? " );
        } finally {
            session.close();
            factory.close();
        }

    }
}