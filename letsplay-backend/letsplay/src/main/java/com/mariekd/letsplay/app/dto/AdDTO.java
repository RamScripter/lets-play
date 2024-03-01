package com.mariekd.letsplay.app.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AdDTO {

    private int id;
    private Date createdAt;
    private String postedBy;
    private String title;
    private String seekingMusicianType;
    private String image;
    private String styleType;
    private String location;
    private String description;

    public AdDTO(int id, Date createdAt, String postedBy, String title,
                 String seekingMusicianType, String image, String styleType,
                 String location, String description) {
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

    public AdDTO() {
    }
}
