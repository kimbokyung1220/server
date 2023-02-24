package com.example.server.service;

import com.example.server.controller.dto.TokenDto;
import com.example.server.controller.request.MemberRequestDto;
import com.example.server.controller.response.MemberResponseDto;
import com.example.server.entity.Member;
import com.example.server.entity.RefreshToken;
import com.example.server.jwt.TokenProvider;
import com.example.server.repository.MemberRepository;
import com.example.server.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
//    private final AuthenticationManager authenticationManager;

    /**
     * 회원가입
     */
    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByMemberid(memberRequestDto.getMemberid())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = memberRequestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    /**
     * 로그인
     */
    @Transactional
    public TokenDto login(MemberRequestDto memberRequestDto, HttpServletResponse response) {
        /**
         * 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
         *  [UsernamePasswordAuthenticationFilter] : 아이디, 패스워드를 이용한 인증을 담당하는 필터
         *    1)사용자가 입력한 ID/PW로 인증정보 객체를 UsernamePasswordAuthenticationToken을 생성
         *    2)아직 인증이 완료된 객체가 아니며 AuthenticationManager 에서 authenticate 메소드의 파라미터로 넘겨서 검증 후에 Authentication를 받음
         */
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        /**
         * 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
         *   1)위 토큰이 유효한지 AuthenticationManager에 위임
         *   2) Builder 에서 UserDetails 의 유저 정보가 서로 일치하는지 검사
         *   인증 프로바이더의 Default : UserDetailsService
         *   3) authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
         *      => 이 결과값을 가지고 Authentication 객체 생성 -> SecurityContext에 저장
         */
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        // header에 Token값 add
        tokenDto.setTokenToHeaders(response);

        // 5. 토큰 발급
        return tokenDto;
    }

//    @Transactional
//    public String login(MemberRequestDto request) throws Exception {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getMemberid(), request.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        UserDetailsImp principal = (UserDetailsImpl) authentication.getPrincipal();
//        return principal.getUsername();
//    }
}
