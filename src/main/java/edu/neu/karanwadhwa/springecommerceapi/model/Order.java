package edu.neu.karanwadhwa.springecommerceapi.model;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private int orderId;
    @CreatedDate
    private Date createdOn;
    private double orderTotal;
    private String status;

    @ElementCollection
    @JoinTable(name = "CUSTOMER_ORDERS", joinColumns = @JoinColumn(name = "USER_ID"))
    @GenericGenerator(name = "sequence_gen", strategy = "sequence")
    @CollectionId(column = @Column(name = "ORDER_ID"), type = @Type(type = "int"), generator = "sequence_gen")
    private Collection<OrderItem> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID")
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
}
