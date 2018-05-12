package udemy.spring.hibernateDemo.entity;

import javax.persistence.*;

@Entity
@Table(name="instructor")
public class Instructor {
    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Instructor(){
    }




    // Relationship with detail table
    @OneToOne(cascade = CascadeType.ALL)

    @JoinColumn(name="instructor_detail_id")
    private InstructorDetail instructorDetail;


    @Id
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Override
    public String toString() {
        return "Instructor {" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='"  + lastName + '\'' +
                ", email='"     + email + '\'' +
                ", detail='"    + instructorDetail +
                '}';
    }

    public int    getId() {
        return id;
    }
    public void   setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void   setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void   setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void   setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {        return instructorDetail;    }
    public void             setInstructorDetail(InstructorDetail instructorDetail) {        this.instructorDetail = instructorDetail;    }
}
