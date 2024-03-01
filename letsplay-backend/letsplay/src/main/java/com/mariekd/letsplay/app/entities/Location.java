package com.mariekd.letsplay.app.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "location")
@Data
public class Location {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false,  name="name")
    private String name;

    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    private Set<Ad> ads;

    public Location(int id, String name, Set<Ad> ads) {
        this.id = id;
        this.name = name;
        this.ads = ads;
    }

    public Location() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ad> getAds() { return ads; }

    public void setAds(Set<Ad> ads) { this.ads = ads; }
}

