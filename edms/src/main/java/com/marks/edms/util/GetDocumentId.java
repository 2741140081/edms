package com.marks.edms.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetDocumentId {
    public static void main(String[] args) {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间: " + now);

        // 定义一个格式器，用于生成19位的日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSS");

        // 使用格式器转换当前时间为19位格式
        String format = now.format(formatter);
        System.out.println("19位数字格式的当前时间: " + format);
    }
}
