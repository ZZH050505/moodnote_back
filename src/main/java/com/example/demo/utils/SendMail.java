package com.example.demo.utils;

import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {
    static Properties properties = new Properties();
    static
    {
        // 设置邮件属性
        properties.setProperty("mail.smtp.host", "smtp.qq.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
    }


    public static void send(String to,String code)
    {
//        System.out.println(System.getProperty("java.classpath"));
        // 收件人邮箱
//        String to = "a2987025642@163.com";

        // 发件人邮箱
        String from = "2987025642@qq.com";

        // 密码 - 建议从环境变量或配置文件中读取
        String password = "gtarxbacggyeddcj";

        // 创建会话
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // 启用调试模式（可选）
        session.setDebug(true);

        try {
            // 创建邮件消息
            MimeMessage message = new MimeMessage(session);

            // 设置发件人
            message.setFrom(new InternetAddress(from));

            // 设置收件人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // 设置主题
            message.setSubject("This is the code to identify your identity");

            // 设置正文
            message.setText(code);

            // 发送邮件
            Transport.send(message);
            System.out.println("邮件发送成功！");

        } catch (MessagingException mex) {
            System.err.println("邮件发送失败：");
            mex.printStackTrace();
        }
    }


    // 生成6位随机英文字母（大小写混合）
    public static String generateRandomLetters()
    {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int)Math.floor(Math.random() * letters.length());
            result.append(letters.charAt(index)) ;
        }
        return result.toString();
    }




}
