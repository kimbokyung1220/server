package com.example.server.service;

import com.example.server.controller.response.ChartResponseDto;
import com.example.server.repository.ChartRepository;
import com.example.server.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class ChartService{


//    private String ChartRepository chartRepository;

    @Transactional(readOnly = true)
    public String getChartAll() {
        System.out.println("AUTH ************************* ");
//      if(SecurityUtil.getCurrentAuth().equals("ADMIN")) {
//          return true;
//      }
//
//        List<Chart> chartList = chartRepository.findAllByOrderByIdDesc();
//        List<ChartResponseDto> chartResponseDtoList = new ArrayList<>();
//
//        for (Chart chart : chartList) {
//            chartResponseDtoList.add(
//                    ChartResponseDto.builder()
//                            .id(chart.getId())
//                            .basicDate(chart.getBasicDate())
//                            .impCnt(chart.getImpCnt())
//                            .clickCnt(chart.getClickCnt())
//                            .convCnt(chart.getConvCnt())
//                            .sellCost(chart.getSellCost())s
//                            .adSpend(chart.getAdSpend())
//                            .build()
//            );
//        }

        return "chart";
    }

}
