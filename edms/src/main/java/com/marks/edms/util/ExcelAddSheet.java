package com.marks.edms.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelAddSheet {
    public static void main(String[] args) throws IOException {
        String path = "D://test//excel//test.xlsx";
        String[] sheetNames = {"1.张敬宇-Whale Zhang",
                "2.黄煜-Geralt Huang",
                "3.黄梦莹-Marilyn Huang",
                "4.严卫佳-Jerry Yan",
                "5.陈俊杰-Jason Chen",
                "6.陈俊杰-Michael Chen",
                "7.易衡-Leon Yi",
                "8.黄志钦-Alex Huang",
                "9.李元芝-Sesame Li",
                "10.陈文森-Jansen Chen",
                "11.刘春林-Truly Liu",
                "12.黎泽君-Chuck Li",
                "13.章成-Netter Zhang",
                "14.徐志豪-Jonathan Xu",
                "15.梁恒辉-Joe Liang",
                "16.卢良坚-Blake Lu",
                "17.郭一鸣-Anson Guo",
                "18.陈晓彤-Ivan Chen",
                "19.李加栋-Garden Li",
                "20.吴旭-Lawrence Wu",
                "21.林成杰-Jerold Lin",
                "22.张丰- Aliko Zhang",
                "23.胡晶晶-Judy Hu",
                "24.黄祺睿-Rex Huang",
                "25.黎浩然-Bill Li",
                "26.欧雪盈-Olivia Ou",
                "27.张文定-Vince Zhang",
                "28.郑兆智-Ralph Zheng",
                "29.欧东坤-Owen Ou",
                "30.张鸿新-Husson Zhang",
                "31.郑泽航-Derek Zheng",
                "32.葛子君-Mia Ge",
                "33.罗天然-Calvin Luo",
                "34.梁吉亮-Jerry Liang",
                "35.谢锦豪-Chase Xie",
                "36.刘显智-Andy Liu",
                "37.吴迪-Woody Wu",
                "38.张凯斌-Eric Zhang",
                "39.范文涛-Jude Fan",
                "40.黄莹莹-Kelly Huang",
                "41.禤永钊-Bosco Xuan",
                "42.蔡衔-Sherman Cai",
                "43.麦海贤-Mike Mai",
                "44.黄学鹏-Longinus Huang"
        };
        addSheet(path,sheetNames);
    }

    public static void addSheet(String path,String[] sheetNames) throws IOException {
        InputStream in = new FileInputStream("D:\\test\\excel\\2023-5 软考人员名单_update01.xlsx");
        OutputStream ops = null;
        ops = new FileOutputStream(path);
        XSSFWorkbook workbook1 = new XSSFWorkbook(in);
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (int i = 0; i < sheetNames.length; i++) {
            XSSFSheet sheet = workbook.createSheet(sheetNames[i]);

        }
//        workbook.cloneSheet(0, String.valueOf(workbook1.getSheet("汇总")));



        workbook.write(ops);
        ops.close();
    }

}
