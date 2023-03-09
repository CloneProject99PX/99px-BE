package com.example.ninetynine.domain.photo.entity;

import com.example.ninetynine.domain.common.entity.Location;
import com.example.ninetynine.domain.common.entity.Category;
import com.example.ninetynine.domain.common.entity.Timestamped;
import com.example.ninetynine.domain.member.entity.Member;
import com.example.ninetynine.domain.photo.dto.PhotoRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column
    private Category category;

    @Column(nullable = false)
    private boolean nsfw;

    private Long view = 0L;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    @JsonIgnore
    private Member member;

    public Photo(PhotoRequestDto photoRequestDto, Member member, String imageUrl){
        title = photoRequestDto.getTitle();
        description = photoRequestDto.getDescription();
        location = photoRequestDto.getLocation();
        category = photoRequestDto.getCategory();
        nsfw = photoRequestDto.isNsfw();
        this.member = member;
        url = imageUrl;
    }
}
