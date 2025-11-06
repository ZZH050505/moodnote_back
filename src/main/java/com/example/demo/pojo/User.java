package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userID;
    private String account;
    private String password;
    private String nickname;
    private String avatarURL;
    private Integer role;
    private java.time.LocalDateTime registerTime;
    private java.time.LocalDateTime lastLoginTime;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}