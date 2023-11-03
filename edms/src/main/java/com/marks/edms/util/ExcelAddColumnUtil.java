package com.marks.edms.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelAddColumnUtil {
    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 10; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","clark");
            jsonObject.put("age",i+10);
            jsonArray.add(jsonObject);
        }
        //读取模板
        try {
            FileInputStream inputStream =new FileInputStream("C:\\Users\\pccw\\Desktop\\EDMS_Doc_Type_Info\\test.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);//获取表中的第一个sheet
            XSSFRow row = sheet.getRow(1);
            XSSFCell cell = row.getCell(0);

            //姓名的样式

            XSSFCellStyle nameStyle = cell.getCellStyle();
            System.out.println(cell.getStringCellValue());//字符串用string

            //年龄的样式
            cell= row.getCell(1);
            XSSFCellStyle ageStyle = cell.getCellStyle();
            System.out.println(cell.getRawValue());//数字用rawValue

            //获取后进行遍历数据库
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //先全部下移动1行，这样才能保证不覆盖
                // (参数1是开始，参数2是结束，参数3是正数代表，开始-结束范围内的单元格下移动1行）
                sheet.shiftRows(3+i,sheet.getLastRowNum(),1);
                //新增行
                XSSFRow rowNew = sheet.createRow(3+i);//参数代表从第几行新增

                //给新增行增加样式
                XSSFCell cellName = rowNew.createCell(0);//第1列，代表name的位置


                XSSFCell cellAge = rowNew.createCell(1);//第2列，代表age的位置


                cellName.setCellStyle(nameStyle);
                cellAge.setCellStyle(ageStyle);

                cellName.setCellValue(jsonObject.getString("name"));
                cellAge.setCellValue(jsonObject.getInteger("age"));

            }
            workbook.write(new FileOutputStream("C:\\Users\\pccw\\Desktop\\EDMS_Doc_Type_Info\\testResult.xlsx"));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
