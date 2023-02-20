package com.example.server.controller;

import com.example.server.controller.dto.TokenDto;
import com.example.server.controller.request.MemberRequestDto;
import com.example.server.controller.request.TokenRequestDto;
import com.example.server.controller.response.MemberResponseDto;
import com.example.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value ="/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping(value ="/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response) {
        return ResponseEntity.ok(authService.login(memberRequestDto, response));
    }

    @PostMapping(value ="/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
