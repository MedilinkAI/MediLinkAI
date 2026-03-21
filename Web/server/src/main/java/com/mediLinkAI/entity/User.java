package com.mediLinkAI.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.mediLinkAI.dto.AccountType;
import com.mediLinkAI.dto.UserDTO;

import jakarta.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public UserDTO toDTO() {
        return new UserDTO(id, name, email, password, accountType);
    }
}