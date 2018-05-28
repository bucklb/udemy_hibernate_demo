package udemy.spring.hibernateDemo.instructorCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Course;
import udemy.spring.hibernateDemo.entity.Instructor;
import udemy.spring.hibernateDemo.entity.InstructorDetail;
import udemy.spring.hibernateDemo.entity.Review;

//
// Explore mappedBy and whether we can go from InstructorDetail to get instructor stuff
//
public class ReadInstructorDetail {

    public static void main(String[] args) {

        System.out.println("Hello World! Reading instructor(s)");
        int instructorDtlId = 3;

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory = new Configuration()
                .configure("hb_01_one_to_one_uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // Detail
        InstructorDetail theInstructorDtl;

        // Get a session from factory
        Session session = factory.openSession();

        // Try and interact with the table.  Get the student by id
        try {
            session.beginTransaction();
            theInstructorDtl = session.get(InstructorDetail.class, instructorDtlId);
            session.getTransaction().commit();

            System.out.println("Instructor Detail : " + theInstructorDtl.toString());
            System.out.println("Instructor        : " + theInstructorDtl.getInstructor().toString());

            System.out.println("retrieved?");
        } finally {
            session.close();
            factory.close();
        }

//        System.out.println(theInstructorDtl.toString());







    }

}
