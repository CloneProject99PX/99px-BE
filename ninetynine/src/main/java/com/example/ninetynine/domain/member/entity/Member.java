package com.example.ninetynine.domain.member.entity;

import com.example.ninetynine.domain.common.entity.Timestamped;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String userName;

    @Column
    private String profilePic;

    @Column
    private Long kakaoId;


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

    public void update(String firstName, String lastName, String userName, String profilePic, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.profilePic = profilePic;
        this.email = email;
    }

}
