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

    @Override
    public String toString() {
        return "Listings{" +
        "listing_id" + listingId +
        '}';
    }
}