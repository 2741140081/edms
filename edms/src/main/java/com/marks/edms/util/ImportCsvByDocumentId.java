package com.marks.edms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImportCsvByDocumentId {
    public static void main(String[] args) throws Exception {
//        String path = "C:\\edms\\batch\\download\\sftp\\MC";
        String path = "C:\\edms\\batch\\download\\sftp\\HK";
        List<String> fileNames = getFileNames(path);
        HashSet<String> set = new HashSet<>();
        for (int j = 0; j < fileNames.size(); j++) {
            String filePathName = path + "\\" +fileNames.get(j);
            FileReader localFile = new FileReader(filePathName);
            BufferedReader reader = new BufferedReader(localFile);

            ArrayList<Map<String, String>> datas = new ArrayList();
            //读取头部
            String readLineHead = reader.readLine();
            readLineHead = readLineHead.toLowerCase(Locale.ROOT);
            String[] titleItems = readLineHead.split(",");

            //读取内容
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                HashMap<String, String> map = new HashMap();
                for (int i = 0; i < titleItems.length; i++) {
                    //去除首尾双引号
                    titleItems[i] = removeStartAndEndQuotes(titleItems[i]);
                    items[i] = removeStartAndEndQuotes(items[i]);

                    map.put(titleItems[i], items[i]);
                }
                datas.add(map);
            }
            for (Map<String, String> data : datas) {
                set.add(data.get("document id"));
            }
        }
        System.out.println(set.size());
    }

    /**
     * 得到文件名称
     *
     * @param path 路径
     * @return {@link List}<{@link String}>
     */
    private static List<String> getFileNames(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        List<String> fileNames = new ArrayList<>();
        return getFileNames(file, fileNames);
    }

    /**
     * 得到文件名称
     *
     * @param file      文件
     * @param fileNames 文件名
     * @return {@link List}<{@link String}>
     */
    private static List<String> getFileNames(File file, List<String> fileNames) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getFileNames(f, fileNames);
            } else {
                fileNames.add(f.getName());
            }
        }
        return fileNames;
    }


    private static String removeStartAndEndQuotes(String str) {
        if(str.startsWith("\"")){
            str = str.substring(1, str.length());
        }
        if(str.endsWith("\"")){
            str = str.substring(0, str.length()-1);
        }

        return str;
    }
}
