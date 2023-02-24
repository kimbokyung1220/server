package com.example.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Chart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String basicDate; //날짜
    @Column(nullable = false) 
    private Long impCnt; //노출수
    @Column(nullable = false)
    private Long clickCnt; //클릭수
    @Column(nullable = false)
    private double convCnt; //전환율(클릭수 / 노출수)
    @Column(nullable = false)
    private Long sellCost; //판매금액
    @Column(nullable = false)
    private Long adSpend; //광고비

}
