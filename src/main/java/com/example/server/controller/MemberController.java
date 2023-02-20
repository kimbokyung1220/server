package com.example.server.controller;

import com.example.server.controller.response.MemberResponseDto;
import com.example.server.service.MemberService;
import com.example.server.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value ="/api/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value ="/me")
    public ResponseEntity<MemberResponseDto> findMemberInfoById() {
        return ResponseEntity.ok(memberService.findMemberInfoById(SecurityUtil.getCurrentMemberId()));
    }

    @GetMapping(value ="/{memberid}")
    public ResponseEntity<MemberResponseDto> findMemberInfoByEmail(@PathVariable String memberid) {
        return ResponseEntity.ok(memberService.findMemberInfoByMemberid(memberid));
    }
}
