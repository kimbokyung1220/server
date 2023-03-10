package com.example.server.repository;

import com.example.server.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChartRepository extends JpaRepository<Chart, Long> {
    List<Chart> findAllByOrderByIdDesc();
}
