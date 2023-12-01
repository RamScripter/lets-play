package com.mariekd.letsplay.entities;

import jakarta.persistence.*;
import java.util.UUID;

import lombok.*;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(updatable = false, nullable = false, name="id")
    @GeneratedValue
    private UUID id;

    @Column(length = 50, nullable = false, name="name")
    private String name;

    @Column(length = 50, nullable = false, name="email")
    private String email;

    @Column(length = 50, nullable = false, name="password")
    private String password;

    @Column(length = 50, nullable = false, name="role")
    private int role;

}
