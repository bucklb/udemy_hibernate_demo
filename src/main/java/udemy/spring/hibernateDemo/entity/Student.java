package udemy.spring.hibernateDemo.entity;

import udemy.spring.hibernateDemo.DateUtils;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="student")
public class Student {

    public Student(String firstName, String lastName, /*Date dateOfBirth,*/ String email) {
        this.firstName = firstName;
        this.lastName = lastName;
//        this.dateOfBirth=dateOfBirth;
        this.email = email;
    }

    public Student(){
    }

    // 28/5/18 - the automatically generated id seems remarkably IMPORTANT
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)   // <-- be SURE to specify this bit !!!!!
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinTable(name="course_student",    // columns used in join & inverse join relate to those in TABLE course_student
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="course_id")
    )
    private List<Course> courses;
    public List<Course> getCourses() {        return courses;    }
    public void setCourses(List<Course> courses) {        this.courses = courses;    }


    // convenience - addCourse
    public void addCourse(Course course) {
        if (courses == null) {            courses = new ArrayList<>();        }
        courses.add(course);
    }

//  Latest iteration does NOT have dob
//    @Column(name="date_of_birth")
//    @Temporal(TemporalType.DATE)
//    private Date dateOfBirth;

    @Column(name="email")
    private String email;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
//                ", dateOfBirth='" + DateUtils.formatDate(dateOfBirth)  + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
