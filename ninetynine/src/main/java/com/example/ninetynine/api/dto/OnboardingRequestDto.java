package com.example.ninetynine.api.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OnboardingRequestDto {

    private String firstName;

    private String lastName;

    private String userName;

    private String profilePic;

    private String email;


}
