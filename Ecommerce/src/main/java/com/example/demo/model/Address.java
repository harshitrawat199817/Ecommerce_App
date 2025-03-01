package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Address {

    @Id
    private final String id = UUID.randomUUID().toString();

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    @Column(nullable = false)
    private boolean isDefault;  // Flag for default address

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

