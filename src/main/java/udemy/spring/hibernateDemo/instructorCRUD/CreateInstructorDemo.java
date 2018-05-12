package udemy.spring.hibernateDemo.instructorCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.DateUtils;
import udemy.spring.hibernateDemo.entity.Instructor;
import udemy.spring.hibernateDemo.entity.InstructorDetail;
import udemy.spring.hibernateDemo.entity.Student;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

public class CreateInstructorDemo {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)        // need factory to know about BOTH classes
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // Get a session from factory
        Session session = factory.openSession();

        // Try and interact with the table
        try {
            // the instructor to create/write (and details). Link them too
            /*
            Instructor theInstructor = new Instructor("quick", "quiz", "q@kew.com");
            InstructorDetail theDetail=new InstructorDetail("x@y.com","bestiality" );
            theInstructor.setInstructorDetail(theDetail);
            */
            Instructor theInstructor = new Instructor("release", "roderick", "r@dgey.com");
            InstructorDetail theDetail=new InstructorDetail("radgey@youtube.com","speech defects" );
            theInstructor.setInstructorDetail(theDetail);

            // the writing
            session.beginTransaction();

            // Instructor & details (latter via CASCADE). Does insert to detail and after that, to instructor
            session.save(theInstructor);

            // make it so!
            session.getTransaction().commit();

            System.out.println("saved instructor?");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
   }
}