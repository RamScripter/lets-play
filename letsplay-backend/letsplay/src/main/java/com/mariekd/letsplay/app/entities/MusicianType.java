package com.mariekd.letsplay.app.entities;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "musician_type")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MusicianType {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false,  name="type")
    private String type;
}
