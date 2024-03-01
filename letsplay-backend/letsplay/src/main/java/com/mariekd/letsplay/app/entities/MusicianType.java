package com.mariekd.letsplay.app.entities;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "musician_type")
@Data
public class MusicianType {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false,  name="type")
    private String name;

    public MusicianType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MusicianType() {
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
}

