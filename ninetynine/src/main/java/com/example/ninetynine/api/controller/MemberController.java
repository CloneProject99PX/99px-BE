package com.example.ninetynine.api.controller;

import com.amazonaws.Response;
import com.example.ninetynine.api.dto.LoginRequestDto;
import com.example.ninetynine.api.dto.OnboardingRequestDto;
import com.example.ninetynine.api.dto.SignupRequestDto;
import com.example.ninetynine.domain.member.service.KakaoService;
import com.example.ninetynine.domain.member.service.MemberService;
import com.example.ninetynine.global.jwt.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    private final KakaoService kakaoService;


    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDto signupRequestDto) {
        memberService.signup(signupRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(signupRequestDto.getEmail());
    }

//    @PostMapping("/auth/email")
//    @ResponseBody
//    public String emailSignup(@RequestBody SignupRequestDto signupRequestDto) {
//        memberService.signup(signupRequestDto);
//        return "success";
//    }
//
    @PutMapping("/auth/onboarding")
    public ResponseEntity<?> onboarding(@RequestBody OnboardingRequestDto onboardingRequestDto, HttpServletRequest request) {
        memberService.onboarding(onboardingRequestDto, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        memberService.login(loginRequestDto, response);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 카카오 로그인
    @GetMapping("/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드
        String createToken = kakaoService.kakaoLogin(code, response);

        // Cookie 생성 및 직접 브라우저에 Set
        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, createToken.substring(7));
        cookie.setPath("/");
        response.addCookie(cookie);

        System.out.println("이게 맞나?!");

        return "redirect:/api/signup";
    }


}
