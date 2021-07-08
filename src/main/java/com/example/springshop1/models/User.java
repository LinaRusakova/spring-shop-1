package com.example.springshop1.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
//TODO: change name of table from "user" to "users"
public class User {
    @Id
    @GenericGenerator(name = "UUID", strategy = "UUIDGenerator")
    @Column(name = "id")
    private UUID id;

//    TODO: change column "name" to "login"
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "second_name")
    private String secondName;


    @Column(name = "phone")
    private String phone;

    //TODO: add column "email"
    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User(String login, String password, String firstName, String
            lastName, String secondName, String phone, String email) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
    }

    public User(String login, String password, String firstName, String
            lastName, String secondName, String phone, String email,
                Collection<Role> roles) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}