package com.example.ninetynine.domain.photo.dto;

import com.example.ninetynine.domain.common.entity.Location;
import com.example.ninetynine.domain.common.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoResponseDto {

    private Long id;

    private String url;

    private String title;

    private String description;

    private Location location;

    private Category category;

    private boolean nsfw;

    private Long view;
}
