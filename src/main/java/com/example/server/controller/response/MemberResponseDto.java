package com.example.server.controller.response;

import com.example.server.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String memberid;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getMemberid());
    }
}
