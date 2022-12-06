package edu.neu.karanwadhwa.springecommerceapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue
    private int userid;
    @Column(nullable = false)
    private String fname;
    private String lname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    private String usertype;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
    @GenericGenerator(name = "sequence_gen", strategy = "sequence")
    @CollectionId(column = @Column(name = "ADDRESS_ID"), type = @Type(type = "int"), generator = "sequence_gen")
    private Collection<Address> addresses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Order> orders = new ArrayList<>();


    public User(){
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid){
        this.userid = userid;
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

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    public void addToOrders(Order order){
        order.setUser(this);
        this.orders.add(order);
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", usertype='" + usertype + '\'' +
                '}';
    }
}
