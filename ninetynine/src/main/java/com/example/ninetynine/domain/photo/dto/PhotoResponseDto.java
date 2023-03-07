package com.example.ninetynine.domain.photo.dto;

import com.example.ninetynine.domain.common.entity.Location;
import com.example.ninetynine.domain.common.entity.Category;
import com.example.ninetynine.domain.photo.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PhotoResponseDto {

    private Long id;

    private String url;

    private String title;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private Location location;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    private boolean nsfw;

    private Long view;

    public PhotoResponseDto(Photo photo){
        this.id = photo.getId();
        this.url = photo.getUrl();
        this.description = photo.getDescription();
        this.location = photo.getLocation();
        this.category = photo.getCategory();
        this.nsfw = photo.isNsfw();
        this.view = photo.getView();
    }
}
