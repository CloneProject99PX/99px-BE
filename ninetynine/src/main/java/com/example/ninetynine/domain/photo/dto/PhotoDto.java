package com.example.ninetynine.domain.photo.dto;

import com.example.ninetynine.domain.common.entity.Address;
import com.example.ninetynine.domain.common.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoDto {

    private Long id;

    private String url;

    private String title;

    private String description;

    private Address address;

    private Category category;

    private boolean nsfw;

    private Long view;
}
