package udemy.spring.hibernateDemo.entity;


import udemy.spring.hibernateDemo.DateUtils;

import javax.persistence.*;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }
    public InstructorDetail() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="youtube_channel")
    private String youtubeChannel;

    @Column(name="hobby")
    private String hobby;

    @Override
    public String toString(){
        return "Detail {" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }

    public int getId() {             return id;    }
    public void setId(int id) {        this.id = id;    }

    public String getYoutubeChannel() {                    return youtubeChannel;    }
    public void   setYoutubeChannel(String youtubeChannel) { this.youtubeChannel = youtubeChannel;    }

    public String getHobby() {          return hobby;    }
    public void   setHobby(String hobby) {this.hobby = hobby;    }

    // Explore bi-directional (with cascade)
    @OneToOne(mappedBy = "instructorDetail",cascade = CascadeType.ALL)
    private Instructor    instructor;

    public  Instructor getInstructor() {                    return instructor;    }
    public void        setInstructor(Instructor instructor) { this.instructor = instructor;    }
}
