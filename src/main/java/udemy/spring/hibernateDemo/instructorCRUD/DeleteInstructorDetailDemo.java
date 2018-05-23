package udemy.spring.hibernateDemo.instructorCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Instructor;
import udemy.spring.hibernateDemo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {
    public static void main( String[] args ) {
        System.out.println("Hello World! Deleting instructor(s) via details");
        int instructorDetailId = 4;

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory = new Configuration()
                .configure("hb_01_one_to_one_uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // Get a session from factory
        Session session = factory.openSession();

        // Try and interact with the table.  Get the student by id
        try {
            session.beginTransaction();

            // Does retrieving as object help?? LOOKS LIKE IT !!! kill the detail, kill the instructor
            InstructorDetail theInstructorDetail=session.get(InstructorDetail.class,instructorDetailId);
            session.delete(theInstructorDetail);

            session.getTransaction().commit();
            System.out.println("deleted?");
        } finally {
            factory.close();
        }

    }

}
