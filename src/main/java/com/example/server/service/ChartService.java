package com.example.server.service;

import com.example.server.controller.response.ChartResponseDto;
import com.example.server.entity.Chart;
import com.example.server.repository.ChartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ChartService {

    private final ChartRepository chartRepository;

    @Transactional(readOnly = true)
    public void getChartAll() {
        List<ChartResponseDto> chartResponseDtoList = new ArrayList<>();
        try {
            List<Chart> chartList = chartRepository.findAllByOrderByIdDesc();


            for (Chart chart : chartList) {
                chartResponseDtoList.add(
                        ChartResponseDto.builder()
                                .id(chart.getId())
                                .basicDate(chart.getBasicDate())
                                .impCnt(chart.getImpCnt())
                                .clickCnt(chart.getClickCnt())
                                .convCnt(chart.getConvCnt())
                                .sellCost(chart.getSellCost())
                                .adSpend(chart.getAdSpend())
                                .build()
                );
            }
        } catch (RuntimeException e) {
            new RuntimeException("admin이 아니에요");
        }

        //return (ResponseEntity<List<ChartResponseDto>>) chartResponseDtoList;
    }

}
