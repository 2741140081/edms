package com.marks.edms.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ExcelInsertColumnUtil {
    public static void main(String[] args) {
        String filepath = "C:\\Users\\pccw\\Desktop\\EDMS_Doc_Type_Info\\Doc_type_Info.xlsx";
        String fileOutpath = "C:\\Users\\pccw\\Desktop\\EDMS_Doc_Type_Info\\Doc_type_Info1.xlsx";
        File file = new File(filepath);
        Set doc_type_id = new HashSet<>();//保存doc_type_id的值

        try {
            OutputStream ou = new FileOutputStream(fileOutpath);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            Workbook wb = new XSSFWorkbook(in);
            Sheet sheet = wb.getSheetAt(0);//获取第一个sheet
            int i = 0;
            int j = sheet.getLastRowNum();

            for (int k = 0; k < j-1 ; k++) {
                Row row = sheet.getRow(k);
                if (row!=null) {
                    Cell cell = row.getCell(0);//获取每一行第一个单元格
                    if (cell != null) {
                        String cellStr = cell.getStringCellValue();
                        System.out.println(cellStr);
                        if (!doc_type_id.contains(cellStr)) {
                            doc_type_id.add(cellStr);
                            sheet.shiftRows(k , sheet.getLastRowNum(),1);
                            Row rowTemp = sheet.getRow(k);
                            Cell cellTemp = rowTemp.createCell(0);
                            cellTemp.setCellValue("test==test");
                            j++;
                        }
                    }
                }

            }

            wb.write(ou);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
