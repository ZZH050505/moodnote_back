package com.example.demo.mapper;

import com.example.demo.pojo.Report;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

@Mapper
public interface SystemMapper {

    public Report getReport(LocalDate  date);
}
