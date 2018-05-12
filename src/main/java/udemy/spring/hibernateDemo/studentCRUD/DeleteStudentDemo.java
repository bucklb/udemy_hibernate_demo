package udemy.spring.hibernateDemo.studentCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Student;

public class DeleteStudentDemo {
    public static void main( String[] args )
    {
        System.out.println( "Update a student!" );
        int studentId=6;

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
            session.createQuery("delete Student where id="+studentId).executeUpdate();
            session.getTransaction().commit();
            System.out.println("deleted? " );
        } finally {
            factory.close();
        }

    }}
