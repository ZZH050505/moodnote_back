package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeRecord {
    private Integer likeID;
    private Integer eventID;
    private Integer userID;
    private LocalDateTime likeTime;
}