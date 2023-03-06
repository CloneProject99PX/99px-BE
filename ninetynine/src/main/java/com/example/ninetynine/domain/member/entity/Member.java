package com.example.ninetynine.domain.member.entity;

import com.example.ninetynine.domain.common.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

//    @Column(nullable = false)
//    private String firstName;
//
//    @Column(nullable = false)
//    private String lastName;
//
//    @Column
//    private String profilePic;

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
