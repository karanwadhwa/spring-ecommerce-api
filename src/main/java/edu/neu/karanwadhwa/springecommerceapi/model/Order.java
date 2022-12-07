package edu.neu.karanwadhwa.springecommerceapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private int orderId;
    @Column(name = "CREATED_ON")
    private Date createdOn = new Date();
    @Column(name = "ORDER_TOTAL")
    private double orderTotal;
    private String status;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "CUSTOMER_ORDERS", joinColumns = @JoinColumn(name = "USER_ID"))
    @GenericGenerator(name = "sequence_gen", strategy = "sequence")
    @CollectionId(column = @Column(name = "ORDER_ID"), type = @Type(type = "int"), generator = "sequence_gen")
    private Collection<OrderItem> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonIgnore // avoid cyclic fetching
    private User user;

    public int getOrderId() {
        return orderId;
    }

    public Collection<OrderItem> getItems() {
        return items;
    }

    public void setItems(Collection<OrderItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
