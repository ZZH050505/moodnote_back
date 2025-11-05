package com.example.demo.utils;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@ConfigurationProperties(prefix = "aliyun.oss")
//这个注解会将yml里面对于前缀的同名属性值注入到实体类中去
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSProperties
{

    private String endpoint;
    private String bucketName;
    private String region;
}
