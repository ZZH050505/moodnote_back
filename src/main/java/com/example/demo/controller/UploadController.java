package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController
{

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

      //本地存储
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException
//    {
//        log.info("接收参数：{},{},{}",name,age,file);
//        //存储到本地
//        //获取原始文件名
//        String originalFilename = file.getOriginalFilename();
//        String extension= originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFileName= UUID.randomUUID().toString()+extension;
//
//        //保存文件
//        file.transferTo(new File("D:/images/"+newFileName));
//
//        return Result.success();
//    }


    @PostMapping("/image")
    public Result upload(MultipartFile file) throws Exception
    {
        log.info("接收参数：{}",file);
        //存储到阿里云oss
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //需要检验格式
        if(!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg"))
        {
            return Result.error("请上传png或者jpg格式的图片");
        }
        String url=aliyunOSSOperator.upload(file.getBytes(),originalFilename);

        return Result.success(url);
    }
}
