package udemy.spring.hibernateDemo.instructorCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Instructor;
import udemy.spring.hibernateDemo.entity.InstructorDetail;
import udemy.spring.hibernateDemo.entity.Student;

public class DeleteInstructorDemo {
    public static void main( String[] args ) {
        System.out.println("Hello World! Deleting instructor(s)");
        int instructorId = 3;

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

            // specify what to delete (by id). DOES NOT SEEM TO DELETE THE DETAILS though ??
            // - may make sense as hibernate does not "know" the details of the related detail if we don't load the instructor
//            session.createQuery("delete Instructor where id="+instructorId).executeUpdate();

            // Does retrieving as object help?? LOOKS LIKE IT !!!
            Instructor theInstructor=session.get(Instructor.class,instructorId);
            session.delete(theInstructor);

            session.getTransaction().commit();
            System.out.println("deleted?");
        } finally {
            factory.close();
        }

    }

}
