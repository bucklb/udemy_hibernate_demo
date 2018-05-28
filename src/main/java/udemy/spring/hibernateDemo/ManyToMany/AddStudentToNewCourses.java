package udemy.spring.hibernateDemo.ManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.*;

//
// Assuming we have created a course with students, would be good to add new courses to a student
//
public class AddStudentToNewCourses {
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
            int studentId=4;
            session.beginTransaction();

            // Grab a student (and check what we grabbed)
            Student student= session.get(Student.class,studentId);
            System.out.println(student);
            System.out.println(student.getCourses());

            System.out.println("Adding student to new courses");
            Course c1=new Course("course One");
            Course c2=new Course("course Two");
            c1.addStudent(student);
            c2.addStudent(student);

            // Saving the course (hopefully with student as reference)
            session.save(c1);
            session.save(c2);

            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }

}
