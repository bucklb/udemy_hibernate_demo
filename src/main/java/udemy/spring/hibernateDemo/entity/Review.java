package udemy.spring.hibernateDemo.entity;

import javax.persistence.*;

@Entity
@Table(name="review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    public int getId() {        return id;    }
    public void setId(int id) {        this.id = id;    }


    @Column(name = "comment")
    private String comment;
    public String getComment() {        return comment;    }
    public void setComment(String comment) {        this.comment = comment;    }

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="course_id")
    private Course course;

    // constructor (APPEARS we must offer a default constructor !!!!)
    // BUT !! it can be a private constructor !!
    public Review(String comment){this.comment=comment;}
    private Review(){}

    @Override
    public String toString() {
        return "Review{" +
                "comment='" + comment + '\'' +
                ", id=" + id +
                '}';
    }
}
