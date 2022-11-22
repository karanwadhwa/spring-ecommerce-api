package edu.neu.karanwadhwa.springecommerceapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID userid;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String usertype;

    public User(){
    }

    public User(String fname, String lname, String email, String password, String usertype) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.usertype = usertype;
    }

    public UUID getUserid() {
        return userid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
