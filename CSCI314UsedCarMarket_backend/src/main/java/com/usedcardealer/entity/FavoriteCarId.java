package com.usedcardealer.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class FavoriteCarId implements Serializable {

    @Column(name = "buyer_id")
    public Long buyerId;

    @Column(name = "car_listing_id")
    public Long carListingId;

    // Constructors
    public FavoriteCarId() {
    }

    public FavoriteCarId(Long buyerId, Long carListingId) {
        this.buyerId = buyerId;
        this.carListingId = carListingId;
    }

    
}