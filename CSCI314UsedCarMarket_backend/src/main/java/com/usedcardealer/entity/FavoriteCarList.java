package com.usedcardealer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favorite_car_lists")
public class FavoriteCarList {

    @EmbeddedId
    public FavoriteCarId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("buyerId")
    @JoinColumn(name = "buyer_id", nullable = false)
    public User buyer;

    @ManyToOne
    @MapsId("carListingId")
    @JoinColumn(name = "car_listing_id", nullable = false)
    public UsedCarListing carListing;

    @Column(name = "added_at", nullable = false)
    public LocalDateTime addedAt;

    // Constructors
    public FavoriteCarList() {
    }

    public FavoriteCarList(User buyer, UsedCarListing carListing) {
        this.buyer = buyer;
        this.carListing = carListing;
        this.id = new FavoriteCarId(buyer.id, carListing.id); 
        this.addedAt = LocalDateTime.now();
    }
}