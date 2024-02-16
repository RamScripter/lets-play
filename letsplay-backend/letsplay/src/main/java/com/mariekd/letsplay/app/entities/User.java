package com.mariekd.letsplay.app.entities;

import jakarta.persistence.*;

import java.util.*;

import lombok.*;

@Entity
@Table(name = "app_user", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "email" }) )
public class User {

    @Id
    @Column(updatable = false, nullable = false, name="user_id")
    @GeneratedValue
    private UUID id;

    @Column(length = 50, nullable = false, name="name")
    private String name;

    @Column(length = 50, nullable = false, name="email")
    private String email;

    @Column(length = 255, nullable = false, name="password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(UUID id, String name, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setRole(Role role) {
        this.roles = roles;
    }

    public Role getRole() {
        return roles.iterator().next();
    }

}
