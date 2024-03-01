package com.mariekd.letsplay.app.dto.mappers;

import com.mariekd.letsplay.app.dto.AdDTO;
import com.mariekd.letsplay.app.entities.Ad;
import com.mariekd.letsplay.authentication.entities.User;

import java.util.UUID;

public class AdMapper {
    public static AdDTO toAdDTO(Ad ad) {
        AdDTO adDTO = new AdDTO();
        adDTO.setId(ad.getId());
        adDTO.setCreatedAt(ad.getCreatedAt());
        adDTO.setPostedBy(UserMapper.toUserDTO(ad.getPostedBy()).getName());
        adDTO.setTitle(ad.getTitle());
        adDTO.setSeekingMusicianType(MusicianTypeMapper.toMusicianDTO(ad.getSeekingMusicianType()).getName());
        adDTO.setImage(ad.getImage());
        adDTO.setStyleType(StyleMapper.toStyleDTO(ad.getStyleType()).getName());
        adDTO.setLocation(LocationMapper.toLocationDTO(ad.getLocation()).getName());
        adDTO.setDescription(ad.getDescription());
        return adDTO;
    }

}
