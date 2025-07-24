package com.postgresql.reverbclone.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
public class Listings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int listing_id;
    private String listing_name;
    private String image_url;
    private Number price;
    private String description;
    private String created_at;
    private String category_id;
    private String seller_id;
    private String status;


    public Listings() {}

    public Listings(String listing_name, String image_url, Number price,
        String description, String created_at, String status) {
    
        this.listing_name = listing_name;
        this.image_url = image_url;
        this.price = price;
        this.description = description;
        this.created_at = created_at;
        this.status = status;
    }

    public int getListingId() {
        return listing_id;
    }

    public void setListingId(int listing_id) {
        this.listing_id = listing_id;
    }

    @Override
    public String toString() {
        return "Listings{" +
        "listing_id" + listing_id +
        '}';
    }
}