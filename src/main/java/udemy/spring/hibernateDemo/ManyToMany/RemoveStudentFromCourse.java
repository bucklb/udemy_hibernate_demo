package udemy.spring.hibernateDemo.ManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.entity.*;

//
// Can link students and courses.  How about unlinking
//
public class RemoveStudentFromCourse {

    // Start with course, get its students and remove one.  May need another case to look at removing a course from a student

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

            // Get the course (and students)
            Course course=session.get(Course.class, courseId);
            System.out.println(course);
            System.out.println(course.getStudents());

            // Get the student to remove
//            Student student=session.get(Student.class,studentId);
            Student student=course.getStudents().get(0);
            System.out.println(student);


            // Remove the student from the course list of students and then save the course
            course.getStudents().remove(student);
            session.save(course);


            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }





}
