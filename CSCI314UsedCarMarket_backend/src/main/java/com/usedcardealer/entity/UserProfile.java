package com.usedcardealer.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // Foreign key to User
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    public User user;

    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String address;

    // Constructors
    public UserProfile() {
    }
}