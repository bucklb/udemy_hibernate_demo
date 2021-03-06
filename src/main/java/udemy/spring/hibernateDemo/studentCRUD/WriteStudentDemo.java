package udemy.spring.hibernateDemo.studentCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import udemy.spring.hibernateDemo.DateUtils;
import udemy.spring.hibernateDemo.entity.*;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class WriteStudentDemo
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // Generate THE factory. ?? How do we share it (and/or its sessions) ??
        SessionFactory factory=new Configuration()
                .configure("hb_01_one_to_one_uni.cfg.xml")
                .addAnnotatedClass(Instructor.class)        // need factory to know about BOTH classes
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)            // added review, so need to link it in. 28/5/18
                .addAnnotatedClass(Student.class)            // added review, so need to link it in. 28/5/18
                .buildSessionFactory();

        // Get a session from factory
        Session session=factory.openSession();

        // Try and interact with the table
        try {
            // the student to create/write
            String sDoB = "11/11/2011";
            Date dDoB = DateUtils.parseDate(sDoB);
            Student theStudent = new Student("quick", "quiz", /*dDoB,*/ "q@kew.com");

            // the writing
            session.beginTransaction();
            session.save(theStudent);
            session.getTransaction().commit();

            System.out.println("saved?");
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }

    }
}
