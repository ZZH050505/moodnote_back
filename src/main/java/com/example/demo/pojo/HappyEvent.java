package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HappyEvent {
    private Integer eventID;//插入时主键不需要前端传递,更改时要前端传递
    private Integer userID;//从token中获取
        private String content;//活动内容
        private Integer isPublic=1;//是否公开  0不公开 1公开
    private Integer status=1;//状态 0隐藏 1正常（发布时默认，只有管理员可以改）
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//修改时间
    //private String imageID;

    private List<EventImage> images;// 图片

    private Long likeCount;
    private Long commentCount;
    private Boolean isLike;
}


