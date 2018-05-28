package udemy.spring.hibernateDemo.studentCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Student;

public class DeleteStudentDemo {
    public static void main( String[] args )
    {
        System.out.println( "Update a student!" );
        int studentId=1;

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
            session.createQuery("delete Student where id="+studentId).executeUpdate();
            session.getTransaction().commit();
            System.out.println("deleted? " );
        } finally {
            session.close();
            factory.close();
        }

    }}
