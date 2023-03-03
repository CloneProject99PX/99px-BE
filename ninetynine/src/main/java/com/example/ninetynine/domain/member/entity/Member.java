package com.example.ninetynine.domain.member.entity;

import com.example.ninetynine.domain.common.entity.Timestamped;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
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





}
