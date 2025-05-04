package com.example.expenseTracker.adaptors.persistence.user_acc;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name="user_acc")
@Getter
@Setter
public class UserAccountJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String email;

    @Column(name = "pwd")
    String password;
    Instant lastLoginAt;

}
