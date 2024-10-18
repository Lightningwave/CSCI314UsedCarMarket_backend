package com.usedcardealer.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User {

    public enum Role {
        ADMIN, AGENT, BUYER, SELLER
    }

    public enum AccountStatus {
        ACTIVE, SUSPENDED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true, nullable = false)
    public String username;

    @Column(nullable = false)
    public String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    public AccountStatus accountStatus = AccountStatus.ACTIVE;

    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    public UserProfile userProfile;

    @JsonManagedReference
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    public Set<UsedCarListing> carListings;

    @JsonIgnore
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    public Set<FavoriteCarList> favoriteCars;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    public Set<Review> reviewsWritten;

    @JsonIgnore
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    public Set<Review> reviewsReceived;

    // Default Constructor
    public User() {
    }

    // Parameterized Constructor
    public User(Long id, String username, String password, Role role, AccountStatus accountStatus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.accountStatus = accountStatus;
    }

    // Method to authenticate user
    public static User authenticateUser(JdbcTemplate jdbcTemplate, String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, User::mapUser, username);

        if (!users.isEmpty()) {
            User user = users.get(0);
            if (password.equals(user.password)) {
                if (user.accountStatus == AccountStatus.ACTIVE) { // Check account status
                    return user;
                } else {
                    throw new RuntimeException("Account is suspended.");
                }
            }
        }
        throw new RuntimeException("Invalid username or password");
    }

    private static User mapUser(ResultSet rs, int rowNum) throws SQLException {
        return new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("password"),
            Role.valueOf(rs.getString("role")),
            AccountStatus.valueOf(rs.getString("account_status"))
        );
    }
}