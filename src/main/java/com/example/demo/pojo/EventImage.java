package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventImage {
    private Integer imageId;//主键，不需要传递
    private Integer eventId;//插入后生成的eventId赋值给这个
    private String imageUrl;//图片地址，前端传递
    private Integer status=1;//状态，0屏蔽，1正常
    private LocalDateTime uploadTime;//上传时间,插入后生成
}