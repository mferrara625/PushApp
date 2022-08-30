package com.mferrara.PushApp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mferrara.PushApp.auth.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
public class Exercise {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String dateTime;

    @OneToMany
    @JoinColumn(name = "exercise_id", referencedColumnName = "id")
    @ElementCollection(targetClass=ExerciseSet.class)
    private List<ExerciseSet> sets = new ArrayList<>();



    public Exercise() {
    }

    public Exercise(String name, List<ExerciseSet> sets, String dateTime) {
        this.name = name;
        this.sets = sets;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExerciseSet> getSets() {
        return sets;
    }

    public void setSets(List<ExerciseSet> sets) {
        this.sets = sets;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
