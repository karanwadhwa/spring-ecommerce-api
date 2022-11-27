package edu.neu.karanwadhwa.springecommerceapi.model;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItem {
    private String name;
    private int quantity;
    private double price;
    private int seller_id;
}
