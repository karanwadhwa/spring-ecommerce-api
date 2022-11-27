package edu.neu.karanwadhwa.springecommerceapi.model;

import javax.persistence.*;

@Entity
@Table(name="PRODUCTS")
public class Product {
    @Id
    @GeneratedValue
    private int productId;
    private String name;
    private int quantity;
    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private User seller;

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
