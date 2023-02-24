package com.example.server.controller.request;

import com.sun.istack.NotNull;

public class ChartRequestDto {
    @NotNull
    private Long id;
    @NotNull
    private String basicDate; //날짜
    @NotNull
    private Long impCnt; //노출수
    @NotNull
    private Long clickCnt; //클릭수
    @NotNull
    private double convCnt; //전환율(클릭수 / 노출수)
    @NotNull
    private Long sellCost; //판매금액
    @NotNull
    private Long adSpend; //광고비
}
