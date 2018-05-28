package udemy.spring.hibernateDemo.courseCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Course;
import udemy.spring.hibernateDemo.entity.Instructor;
import udemy.spring.hibernateDemo.entity.InstructorDetail;

public class CreateCourseDemo{
    public static void main(String[] args) {

        int instructorId=2;
        Instructor theInstructor=null;

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory = new Configuration()
                .configure("hb_01_one_to_one_uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)        // need factory to know about BOTH classes
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // Get a session from factory
        Session session = factory.openSession();

        // Try and interact with the table.  Get the student by id
        try {

            // Want an instructor for the courses
            theInstructor = session.get(Instructor.class, instructorId);
            System.out.println("instructor " + instructorId + " " + theInstructor);

            // Create some courses for the instructor & add them to instructor
            // (so the course "know" about their instructor prior to the save we'll do)
            // - might perhaps do via an array. So create array of courses, add each to constructor and save each
//            theInstructor=new Instructor("fred","flintstone","f@f.f");
            Course c1=new Course("B QIK");
            Course c2=new Course("B QIK 2");
            theInstructor.add(c1);
            theInstructor.add(c2);

            // the writing
            session.beginTransaction();

            // save the courses (including their knowledge of the instructor).  Can't cascade the save from the instructor ??
            // NOT even if we create a new instructor :(
            session.save(c1);
            session.save(c2);
//            session.save(theInstructor);

            // make it so!
            session.getTransaction().commit();


        } finally {
            session.close();
            factory.close();
        }


        }
}
