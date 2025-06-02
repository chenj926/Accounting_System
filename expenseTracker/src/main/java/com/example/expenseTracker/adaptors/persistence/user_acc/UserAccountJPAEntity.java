package com.example.expenseTracker.adaptors.persistence.user_acc;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name="user_acc")
@Getter
@Setter
public class UserAccountJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    Long id;

    String username;

    @Email
    @Column(unique = true)
    String email;

    @Column(name = "pwd")
    String password;

    Instant lastLoginAt;

    // precision: 17 digit before decimal
    // scale: 2 decimal place
    @Column(name = "total_income", precision = 19, scale = 2)
    private BigDecimal totalIncome = BigDecimal.ZERO;

    @Column(name = "total_outflow", precision = 19, scale = 2)
    private BigDecimal totalOutflow = BigDecimal.ZERO;

    @Column(name = "balance", precision = 19, scale = 2) // Net balance
    private BigDecimal balance = BigDecimal.ZERO;

}
