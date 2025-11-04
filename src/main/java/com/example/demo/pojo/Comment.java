package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer commentID;
    private Integer eventID;
    private Integer userID;
    private String content;
    private Integer status;
    private LocalDateTime commentTime;
}