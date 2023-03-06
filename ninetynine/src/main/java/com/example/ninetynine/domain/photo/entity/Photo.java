package com.example.ninetynine.domain.photo.entity;

import com.example.ninetynine.domain.common.entity.Location;
import com.example.ninetynine.domain.common.entity.Category;
import com.example.ninetynine.domain.common.entity.Timestamped;
import com.example.ninetynine.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Photo extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column
    private Category category;

    @Column(nullable = false)
    private boolean nsfw;

    private Long view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;


}
