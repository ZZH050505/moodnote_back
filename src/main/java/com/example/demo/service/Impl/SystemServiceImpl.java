package com.example.demo.service.Impl;

import com.example.demo.mapper.SystemMapper;
import com.example.demo.pojo.Report;
import com.example.demo.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private SystemMapper systemMapper;

    @Override
    public Report getReport() {
        LocalDate now = LocalDate.now();
        Report report = systemMapper.getReport(now);
        log.info("getReport");
        return report;
    }
}
