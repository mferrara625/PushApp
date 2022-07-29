package com.mferrara.PushApp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private Integer weight;
    private Integer sets;
    private Integer expectedReps;
    private Integer restTime;
    @ElementCollection(targetClass=Integer.class)
    private List<Integer> actualReps;

    public Exercise() {
    }

    public Exercise(String name, Integer weight, Integer sets, Integer expectedReps, Integer restTime) {
        this.name = name;
        this.weight = weight;
        this.sets = sets;
        this.expectedReps = expectedReps;
        this.restTime = restTime;
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getExpectedReps() {
        return expectedReps;
    }

    public void setExpectedReps(Integer expectedReps) {
        this.expectedReps = expectedReps;
    }

    public Integer getRestTime() {
        return restTime;
    }

    public void setRestTime(Integer restTime) {
        this.restTime = restTime;
    }

    public List<Integer> getActualReps() {
        return actualReps;
    }

    public void setActualReps(List<Integer> actualReps) {
        this.actualReps = actualReps;
    }
}
