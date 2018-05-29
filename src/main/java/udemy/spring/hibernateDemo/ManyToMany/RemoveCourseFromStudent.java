package udemy.spring.hibernateDemo.ManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.*;

import java.util.ArrayList;

//
// Have removed a student from course.  Try a different approach starting with student
//
public class RemoveCourseFromStudent {

    public static void main(String[] args) {

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
            int courseId=1;
            int studentId=4;
            session.beginTransaction();


            // Get the student to remove
            Student student=session.get(Student.class,studentId);
            System.out.println(student);

            // Effectively remove all the courses (give student an empty list)
            student.setCourses(new ArrayList<Course>());
            session.save(student);

            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }

}
