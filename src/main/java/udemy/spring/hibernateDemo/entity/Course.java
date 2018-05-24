package udemy.spring.hibernateDemo.entity;

import javax.persistence.*;

// Added new table so start using it
@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="title")
    private String title;

    // Don't allow REMOVE, so have to spell out all the others .....
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    public int getId() {        return id;    }
    public void setId(int id) {        this.id = id;    }

    public String getTitle() {        return title;    }
    public void setTitle(String title) {        this.title = title;    }

    public Instructor getInstructor() {        return instructor;    }
    public void setInstructor(Instructor instructor) {        this.instructor = instructor;    }

    public Course(String title) {
        this.title = title;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
