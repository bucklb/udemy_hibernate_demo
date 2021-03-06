package udemy.spring.hibernateDemo.courseCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.*;

public class ReadInstructorWithCoursesDemo {
    public static void main(String[] args) {

        int instructorId=1;
        Instructor theInstructor=null;

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory = new Configuration()
                .configure("hb_01_one_to_one_uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)        // need factory to know about BOTH classes
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)            // added review, so need to link it in. 28/5/18
                .addAnnotatedClass(Student.class)            // added review, so need to link it in. 28/5/18
                .buildSessionFactory();

        // Get a session from factory
        Session session = factory.openSession();

        // Try and interact with the table.  Get the student by id
        try {

            // Retrieve the instructor, replete with courses
            session.beginTransaction();
            theInstructor = session.get(Instructor.class, instructorId);
            session.getTransaction().commit();

            System.out.println("Instructor : " + theInstructor.toString());
            for(Course c:theInstructor.getCourses()){
                System.out.println(c.toString());
                System.out.println(c.getReviews().toString());
            }

            System.out.println("retrieved?");
        } finally {
            session.close();
            factory.close();
        }







    }
}
