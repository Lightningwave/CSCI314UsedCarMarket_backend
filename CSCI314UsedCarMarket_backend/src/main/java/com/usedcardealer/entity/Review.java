package com.usedcardealer.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // Foreign key to User (author)
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    public User author;

    // Foreign key to User (agent)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    public User agent;

    public int score; // Rating score between 1 and 5

    @Column(columnDefinition = "TEXT")
    public String content;

    public LocalDateTime timestamp;

    // Constructors
    public Review() {
    }

    @PrePersist
    public void prePersist() {
        timestamp = LocalDateTime.now();
    }
}