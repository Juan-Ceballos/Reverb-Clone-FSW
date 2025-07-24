package com.postgresql.reverbclone.model;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
public class Listings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "listing_id")
    private int listingId;
    @Column(name = "listing_name")
    private String listingName;
    @Column(name = "image_url")
    private String imageURL;
    private Number price;
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "seller_id")
    private String sellerId;
    private String status;


    public Listings() {}

    public Listings(String listingName, String imageURL, Number price,
        String description, LocalDateTime createdAt, String status) {
    
        this.listingName = listingName;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.status = status;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public String getListingName() {
        return listingName;
    }

    public void setListingName(String listingName) {
        this.listingName = listingName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Listings{" +
                "listingId=" + listingId +
                ", listingName='" + listingName + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", categoryId='" + categoryId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}