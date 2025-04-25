package com.book.dockerproject.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name="lectures")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int credit;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<StudentLecture> studentLectures;

    public Lecture(){};
    public Lecture(Long id, String name, int credit) {
        this.id=id;
        this.name = name;
        this.credit=credit;
    }
}
