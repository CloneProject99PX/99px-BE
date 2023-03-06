package com.example.ninetynine.domain.member.entity;

import com.example.ninetynine.domain.common.entity.Timestamped;
import com.example.ninetynine.domain.photo.entity.Photo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 객체의 무분별한 생성 방지
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    private Long kakaoId;

    @OneToOne
    @JoinColumn(name = "memberInfo_id")
    private MemberInfo memberInfo;


    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Member(String email, String password, Long kakaoId) {
        this.email = email;
        this.password = password;
        this.kakaoId = kakaoId;
    }

    public Member kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }

}
