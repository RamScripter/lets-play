package com.mariekd.letsplay.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    @Id
    @Column(updatable = false, nullable = false, name="role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, name="type")
    private String role;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    @Override
    public String toString() {
        return "ROLE_" + role; // or any other representation you prefer
    }

    public String getName() {
        return role;
    }
}
