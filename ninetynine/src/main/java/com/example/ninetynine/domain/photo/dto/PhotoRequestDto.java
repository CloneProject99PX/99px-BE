package com.example.ninetynine.domain.photo.dto;

import com.example.ninetynine.domain.common.entity.Category;
import com.example.ninetynine.domain.common.entity.Location;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class PhotoRequestDto {
    private String title;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private Location location;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    private boolean nsfw;
}
