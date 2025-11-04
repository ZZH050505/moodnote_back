package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventImage {
    private Integer imageID;
    private Integer eventID;
    private String imageURL;
    private String thumbnailURL;
    private Integer imageSize;
    private String imageType;
    private Integer status;
    private LocalDateTime uploadTime;
}