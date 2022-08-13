package com.mferrara.PushApp.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mferrara.PushApp.models.Exercise;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 6, max = 42)
    private String username;

    @NotBlank
    @Size(min = 8, max = 99)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Exercise> completedExercises = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Set<Exercise> exercises = new HashSet<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public List<Exercise> getCompletedExercises() {
        return completedExercises;
    }

    public void setCompletedExercises(List<Exercise> completedExercises) {
        this.completedExercises = completedExercises;
    }

    public void addToExercises(Exercise exercise) {
        exercises.add(exercise);
    }

    public void addToCompletedExercises(Exercise exercise){
        completedExercises.add(exercise);
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }
}
