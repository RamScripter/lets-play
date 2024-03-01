package com.mariekd.letsplay.app.dto;

import com.mariekd.letsplay.app.entities.Ad;

public class AdMapper {
    public static AdDTO toAdDTO(Ad ad) {
        AdDTO adDTO = new AdDTO();
        adDTO.setId(ad.getId());
        adDTO.setCreatedAt(ad.getCreatedAt());
        adDTO.setPostedBy(UserMapper.toUserDTO(ad.getPostedBy()).getName());
        adDTO.setTitle(ad.getTitle());
        adDTO.setSeekingMusicianType(ad.getSeekingMusicianType());
        adDTO.setImage(ad.getImage());
        adDTO.setStyleType(ad.getStyleType());
        adDTO.setLocation(ad.getLocation());
        adDTO.setDescription(ad.getDescription());
        return adDTO;
    }
}
