package com.example.ninetynine.api.dto;

import com.example.ninetynine.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String userName;

    private String profilePic;

    public MemberResponseDto(Member member){
        id = member.getId();
        email = member.getEmail();
        firstName = member.getFirstName();
        lastName = member.getLastName();
        userName = member.getUserName();
        profilePic = member.getProfilePic();
    }
}
