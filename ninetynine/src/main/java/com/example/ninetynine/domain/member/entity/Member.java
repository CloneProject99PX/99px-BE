package com.example.ninetynine.domain.member.entity;

import com.example.ninetynine.domain.common.entity.Timestamped;
import com.example.ninetynine.domain.photo.entity.Photo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String profilePic;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

}
