package com.usedcardealer.entity;

import java.math.BigDecimal;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;

@Entity
@Table(name = "used_car_listings")
public class UsedCarListing {

    public enum Status {
        AVAILABLE, SOLD, SUSPENDED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // Foreign key to User (agent)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    public User agent;

    public String title;
    public String description;
    public String make;
    public String model;
    public int year;
    
    @Column(nullable = false, precision = 15, scale = 2)
    public BigDecimal price;
    
    public String image; // URL or path to image
    public BigDecimal depreciationPerYear;
    public LocalDate registrationDate;
    public Integer mileage;
    public Integer coeLeft; // Months left on COE
    public Integer numberOfOwners;
    public Integer engineCc;
    public String engineType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Status status = Status.AVAILABLE;

    public Integer viewCount = 0;
    public Integer shortlistCount = 0;

    // Constructors
    public UsedCarListing() {
    }
}