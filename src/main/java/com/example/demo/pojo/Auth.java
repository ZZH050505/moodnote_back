package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    private String account;
    private String password;
    private String nickname;
    private String verificationCode;
    private String token;
}
