package com.mariekd.letsplay.authentication.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "app_user")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @Column(updatable = false, nullable = false, name="id")
    @GeneratedValue
    private UUID id;

    @Column(length = 50, nullable = false, name="name")
    private String name;

    @Column(length = 50, nullable = false, name="email")
    private String email;

    @Column(length = 255, nullable = false, name="password")
    private String password;

    @ManyToOne
    @JoinColumn(nullable = false, name="role")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRole()));
    }

    @Override
    public String getUsername() {
        return email; // return name ?
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
