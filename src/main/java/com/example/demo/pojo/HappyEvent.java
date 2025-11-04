package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HappyEvent {
    private Integer eventID;
    private Integer userID;
    private LocalDate eventDate;
    private String content;
    private Integer isPublic;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}