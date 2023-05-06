package com.encryptlab14.models;


import jakarta.persistence.*;
import java.util.List;
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String username;
    String password;
    @OneToMany(mappedBy = "siteUser", cascade = CascadeType.ALL)
    List<Post> postList;

    public SiteUser() {
        // empty
    }
    public SiteUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
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

    public void addPost(Post post) {
        post.setSiteUser(this);
        postList.add(post);
    }

    public List<Post> getPostList() {
        return postList;
    }

    @Override
    public String toString() {
        return "SiteUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", postList=" + postList +
                '}';
    }
}
