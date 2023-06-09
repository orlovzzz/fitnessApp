package org.example.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "subscription")
    private Date subscription;

    @Column(name = "coach")
    private String coach;

    @Column(name = "balance")
    private int balance = 0;

    public Person() {
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Long getId() {
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

    public Date getSubscription() {
        return subscription;
    }

    public void setSubscription(Date subscription) {
        this.subscription = subscription;
    }
}