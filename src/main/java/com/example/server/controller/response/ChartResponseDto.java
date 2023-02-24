package com.example.server.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChartResponseDto {

    private Long id;

    private String basicDate; //날짜

    private Long impCnt; //노출수

    private Long clickCnt; //클릭수

    private double convCnt; //전환율(클릭수 / 노출수)

    private Long sellCost; //판매금액

    private Long adSpend; //광고비
}
