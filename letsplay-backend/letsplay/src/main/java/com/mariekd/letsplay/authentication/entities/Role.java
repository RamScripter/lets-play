package com.mariekd.letsplay.authentication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    @Id
    @Column(updatable = false, nullable = false, name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, name="type")
    private String role;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<User> users;

    @Override
    public String toString() {
        return "ROLE_" + role; // or any other representation you prefer
    }
}
