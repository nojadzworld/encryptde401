package com.encryptlab14.models;

import jakarta.persistence.*;
import java.util.Date;
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(columnDefinition = "text")
    String postContent;
    Date date;

    @ManyToOne
    @JoinColumn(name = "siteUser_id")
    SiteUser siteUser;

    protected Post() {
        //empty
    }


    public Post(String postContent, Date date) {
        this.postContent = postContent;
        this.date = date;
    }



    public long getId() {
        return id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }
}
