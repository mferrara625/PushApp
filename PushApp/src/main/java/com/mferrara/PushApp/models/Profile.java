package com.mferrara.PushApp.models;

import com.mferrara.PushApp.auth.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Profile {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Content> content;


    @OneToOne
    @JoinColumn(
            name = "users_id",
            referencedColumnName = "id"
    )
    private User user;

    public Profile() {
    }

    public Profile(Long id, String name, String description, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
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

    public void setName(String name)  {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}
