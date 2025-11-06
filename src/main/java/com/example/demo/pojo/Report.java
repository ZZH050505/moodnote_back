package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private Long totalUsers;
    private Long totalEvents;
    private Long totalImages;
    private Long totalComments;
    private Long todayActiveUsers;

}
