package com.example.demo.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "volunteer")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String username;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private Date dateofbirth;
    private Date dateofreg;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public Date getDateofreg() {
        return dateofreg;
    }

    public void setDateofreg(Date dateofreg) {
        this.dateofreg = dateofreg;
    }

    public Volunteer() {}
    
    public Volunteer(String username, String firstname, String lastname, String phone, String email, Date dateOfBirth, Date dateOfRegistration) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.dateofbirth = dateOfBirth;
        this.dateofreg = dateOfRegistration;
    }



    @Override
    public String toString() {
        return "Volunteer{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateofbirth +
                ", dateOfRegistration=" + dateofreg +
                '}';
    }

}