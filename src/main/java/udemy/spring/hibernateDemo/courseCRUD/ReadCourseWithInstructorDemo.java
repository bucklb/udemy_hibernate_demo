package udemy.spring.hibernateDemo.courseCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.Course;
import udemy.spring.hibernateDemo.entity.Instructor;
import udemy.spring.hibernateDemo.entity.InstructorDetail;

//
// Not part of the Udemy stuff, but want to see if getting course gives easy access to its instructor
//
public class ReadCourseWithInstructorDemo {
    public static void main(String[] args) {

        int courseId=11;

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

            // Retrieve the course (by id) and any corresponding instructor
            session.beginTransaction();
            Course theCourse = session.get(Course.class, courseId);
            session.getTransaction().commit();

            // dump the course (and its instructor) to screen
            System.out.println("Course : " + theCourse.toString());
            System.out.println(theCourse.getInstructor().toString());

        } finally {
            factory.close();
        }



    }
}
