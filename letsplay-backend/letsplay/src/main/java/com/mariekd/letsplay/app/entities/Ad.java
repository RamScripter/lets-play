package com.mariekd.letsplay.app.entities;

import jakarta.persistence.*;
import java.util.Date;

import lombok.*;

@Entity
@Table(name = "ad")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ad {
    @Id
    @Column(updatable = false, nullable = false)
    private Integer id;

    @Column(nullable = false, name="created_at")
    private Date createdAt;

    @Column(nullable = false, name="posted_by")
    private Integer postedBy;

    @Column(nullable = false, name="title")
    private String title;

    @Column(nullable = false, name="seeking_type")
    private Integer seekingType;

    @Column(nullable = false, name="seeking_musician_type")
    private Integer seekingMusicianType;

    @Column(nullable = false, name="image")
    private String image;

    @Column(nullable = false, name="style_type")
    private Integer styleType;

    @Column(nullable = false, name="location")
    private String location;

    @Column(nullable = false, name="description")
    private String description;
}
