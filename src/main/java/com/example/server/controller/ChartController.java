package com.example.server.controller;

import com.example.server.controller.response.ChartResponseDto;
import com.example.server.service.ChartService;
import com.example.server.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ChartController {
    private final ChartService chartService;

    @GetMapping(value = "/chart")
    public void getChartAll () {
        chartService.getChartAll();
    }

}
