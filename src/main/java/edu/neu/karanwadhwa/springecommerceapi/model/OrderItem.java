package edu.neu.karanwadhwa.springecommerceapi.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderItem {
    private String name;
    private int quantity;
    private double price;
    @Column(name = "TOTAL_PRICE")
    private double totalPrice;
    @Column(name = "SELLER_ID")
    private int sellerId;
    @Column(name = "PRODUCT_ID")
    private int productId;
    private String thumbnail_url;


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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }
}
