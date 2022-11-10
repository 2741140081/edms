package com.marks.edms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
public class UploadController {
    private final static String FILE_UPLOAD_PATH = "C:\\edms\\upload\\";

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file")MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败";
        }
        String filename = file.getOriginalFilename();
        String suffixname = filename.substring(filename.lastIndexOf("."));

//        生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixname);

        String newFileName = tempName.toString();

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FILE_UPLOAD_PATH + newFileName);
            Files.write(path,bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "上传成功，地址为: /upload/"+newFileName;

    }

}
