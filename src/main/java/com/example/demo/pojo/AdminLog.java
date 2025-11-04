package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLog {
    private Integer logID;
    private Integer adminID;
    private String operationType;
    private String operationContent;
    private Integer targetID;
    private LocalDateTime operationTime;
    private String ipAddress;
}