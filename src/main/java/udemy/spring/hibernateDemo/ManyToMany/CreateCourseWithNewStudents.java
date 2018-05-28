package udemy.spring.hibernateDemo.ManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.*;

//
// Added mant-many between courses & students.  Time to exercise link(s)
//
public class CreateCourseWithNewStudents {

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

            session.beginTransaction();

            // Build the course and save it
            Course course=new Course("Z course for students");
            System.out.println("Saving course");
            session.save(course);

            // ?? Need instructor or not ??
////            System.out.println("Creating instructor & students");
////            Instructor instructor=new Instructor("xxx","yyy","x@y.com");


            // create some students & add them to the course
            System.out.println("Student creation");
            Student s1=new Student("aaron","aardvark","a@a.com");
            Student s2=new Student("bart","badger","b@b.com");

            // add students to course
            System.out.println("adding students to course");
            course.addStudent(s1);
            course.addStudent(s2);

////            System.out.println("Saving instructor");
////            session.save(instructor);

            // save the students
            System.out.println("Saving student s, having added them to couse");
            session.save(s1);
            session.save(s2);

            System.out.println("Should have finished saving");

            // make it so!
            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }
}
