package com.marks.edms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UploadController {
    private final static String FILE_UPLOAD_PATH = "C:\\edms\\upload\\";
    @Autowired
    private StandardServletMultipartResolver standardServletMultipartResolver;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file")MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败";
        }
        String filename = file.getOriginalFilename();
        String suffixname = filename.substring(filename.lastIndexOf("."));

        //生成文件名称通用方法
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

    @RequestMapping(value = "/uploadFilesBySameName", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFilesBySameName(@RequestPart MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return "参数异常";
        }
        if (files.length > 5) {
            return "最多上传5个文件";
        }
        String uploadResult = "文件上传成功，地址为";

        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            if (StringUtils.isEmpty(filename)) {
                //表示无文件信息
                continue;
            }

            String suffixname = filename.substring(filename.lastIndexOf("."));

            //生成文件名称通用方法
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Random r = new Random();
            StringBuilder tempName = new StringBuilder();
            tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixname);

            String newFileName = tempName.toString();

            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(FILE_UPLOAD_PATH + newFileName);
                Files.write(path,bytes);
                uploadResult += "/upload/" + newFileName +"<br>";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
        return uploadResult;
    }

    @RequestMapping(value = "/uploadFilesByDifferentName", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFilesByDifferentName(HttpServletRequest httpServletRequest) {
        List<MultipartFile> multipartFiles = new ArrayList<>(8);
        if (!standardServletMultipartResolver.isMultipart(httpServletRequest)) {
            return "请选择文件";
        }
        //
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) httpServletRequest;
        Iterator<String> iterator = multiRequest.getFileNames();
        int total = 0;
        while (iterator.hasNext()) {
            if (total > 5) {
                return "最多上传5个文件";
            }
            total += 1;
            MultipartFile file = multiRequest.getFile(iterator.next());
            multipartFiles.add(file);
        }
        if (CollectionUtils.isEmpty(multipartFiles)) {
            return "请选择文件";
        }

        if (multipartFiles != null && multipartFiles.size() > 5) {
            return "最多上传5个文件";
        }

        String uploadResult = "文件上传成功，地址为";

        for (int i = 0; i < multipartFiles.size(); i++) {
            String filename = multipartFiles.get(i).getOriginalFilename();
            if (StringUtils.isEmpty(filename)) {
                //表示无文件信息
                continue;
            }

            String suffixname = filename.substring(filename.lastIndexOf("."));

            //生成文件名称通用方法
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Random r = new Random();
            StringBuilder tempName = new StringBuilder();
            tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixname);

            String newFileName = tempName.toString();

            try {
                byte[] bytes = multipartFiles.get(i).getBytes();
                Path path = Paths.get(FILE_UPLOAD_PATH + newFileName);
                Files.write(path, bytes);
                uploadResult += "/upload/" + newFileName + "<br>";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return uploadResult;


    }


}
