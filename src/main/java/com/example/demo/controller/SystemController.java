package com.example.demo.controller;

import com.example.demo.pojo.Report;
import com.example.demo.pojo.Result;
import com.example.demo.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SystemController {
    @Autowired
    private SystemService systemService;
    @RequestMapping("/admin/report")
    public Result getReport()
    {
        Report report=systemService.getReport();
        return Result.success(report);
    }



}
