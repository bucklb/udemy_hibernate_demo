package udemy.spring.hibernateDemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    // Use the "local" instructor_id to join with instructors
    // (assume that hibernate gets the table name from the Class we use, so goes to Instructor class to get its details, like table name)
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    // Use course_id (in Review table) to do the join
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name="course_id")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Review> reviews;


    public int getId() {        return id;    }
    public void setId(int id) {        this.id = id;    }

    public String getTitle() {        return title;    }
    public void setTitle(String title) {        this.title = title;    }

    public Instructor getInstructor() {        return instructor;    }
    public void setInstructor(Instructor instructor) {        this.instructor = instructor;    }

    // Constructors
    public Course(String title) {        this.title = title;    }
    public Course() {    }

    public void setReviews(List<Review> reviews) {        this.reviews = reviews;    }
    public List<Review> getReviews() {        return reviews;    }

    public void add(Review review){
        if(reviews == null) { reviews=new ArrayList<>();}
        reviews.add(review);
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
