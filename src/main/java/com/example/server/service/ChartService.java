package com.example.server.service;

import com.example.server.controller.dto.CustomResponseDto;
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
    public CustomResponseDto<List<ChartResponseDto>> getChartAll() {

        List<Chart> chartList = chartRepository.findAllByOrderByIdDesc();
        List<ChartResponseDto> chartResponseDtoList = new ArrayList<>();

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


        return CustomResponseDto.success(chartResponseDtoList, "grid 조회");
    }

}
