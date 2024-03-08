package com.mariekd.letsplay.app.entities;

import com.mariekd.letsplay.app.dto.mappers.MusicianTypeMapper;
import com.mariekd.letsplay.authentication.entities.User;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import lombok.*;

@Entity
@Table(name = "ad")
@Data
public class Ad {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name="created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "posted_by", nullable = false, referencedColumnName = "user_id")
    private User postedBy;

    @Column(nullable = false, name="title")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="seeking_musician_type", nullable = false, referencedColumnName = "id")
    private MusicianType seekingMusicianType;

    @Column(nullable = false, name="image")
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="style_type", nullable = false, referencedColumnName = "id")
    private Style styleType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="location", nullable = false, referencedColumnName = "id")
    private Location location;

    @Column(nullable = false, name="description")
    private String description;

    public Ad(int id, Date createdAt, User postedBy, String title, MusicianType seekingMusicianType, String image, Style styleType, Location location, String description) {
        this.id = id;
        this.createdAt = createdAt;
        this.postedBy = postedBy;
        this.title = title;
        this.seekingMusicianType = seekingMusicianType;
        this.image = image;
        this.styleType = styleType;
        this.location = location;
        this.description = description;
    }

    public Ad() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MusicianType getSeekingMusicianType() {
        return seekingMusicianType;
    }

    public void setSeekingMusicianType(MusicianType seekingMusicianType) {
        this.seekingMusicianType = seekingMusicianType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Style getStyleType() {
        return styleType;
    }

    public void setStyleType(Style styleType) {
        this.styleType = styleType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", postedBy=" + postedBy +
                ", title='" + title + '\'' +
                ", seekingMusicianType=" + seekingMusicianType +
                ", image='" + image + '\'' +
                ", styleType=" + styleType +
                ", location=" + location +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return id == ad.id && createdAt.equals(ad.createdAt) && postedBy.equals(ad.postedBy) && title.equals(ad.title) && seekingMusicianType.equals(ad.seekingMusicianType) && image.equals(ad.image) && styleType.equals(ad.styleType) && location.equals(ad.location) && description.equals(ad.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, postedBy, title, seekingMusicianType, image, styleType, location, description);
    }
}
