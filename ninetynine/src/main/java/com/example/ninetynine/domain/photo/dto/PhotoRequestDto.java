package com.example.ninetynine.domain.photo.dto;

import com.example.ninetynine.domain.common.entity.Category;
import com.example.ninetynine.domain.common.entity.Location;
import lombok.Getter;

@Getter
public class PhotoRequestDto {
    private String title;

    private String description;

    private Location location;

    private Category category;

    private boolean nsfw;
}
