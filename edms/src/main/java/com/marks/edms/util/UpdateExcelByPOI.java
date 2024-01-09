package com.marks.edms.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class UpdateExcelByPOI {
    public static void main(String[] args) {
        File file = new File("D:\\test\\excel\\WO Info.xlsx");
        modifyExcel(file);
    }

    private static void modifyExcel(File file) {
        long begin = System.currentTimeMillis();
        FileOutputStream fileOutputStream = null;
        Integer number = 1;
        try {
            // 读取Excel文件
            Integer rowNum = 0;
            Integer cellNum = 0;

            try (
                    // 读取xlsx类型文件
                    Workbook workbook = new XSSFWorkbook(Files.newInputStream(file.toPath()))) {
                // 选择处理的sheet
                String page = "Sheet1";
                Sheet sheet = workbook.getSheet(page);
                // 下面是我这边对某个数据列的处理
                for (int j = 0; j < 100; j++) {
                    Row row = sheet.getRow(0);
                    Cell cell = row.getCell(j);
                    // 定位到初始行和列
                    if (cell != null && cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals("Attachment File")) {
                        cellNum = j;
                        rowNum = 1;
                        break;
                    }
                }

                // 行不变，对列进行遍历处理
                for (int i = rowNum; i < 9235; i++) {
                    Row row = sheet.getRow(i);
                    number = i;
                    Cell cell = row.getCell(cellNum);
                    if (cell.getCellType() == CellType.STRING && !cell.getStringCellValue().equals("#N/A")) {
                        Map<String, String> stringMap = re(cell.getStringCellValue());
                        // 处理完后覆盖原值
                        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                            Cell cellAttName = row.createCell(cellNum + 1);
                            Cell cellAttPath = row.createCell(cellNum + 2);
                            cellAttName.setCellValue(entry.getValue());
                            cellAttPath.setCellValue(entry.getKey());
                            cellNum = cellNum + 2;
                        }
                        cellNum = 61;
                    }
                }
                // 写回原文件
                fileOutputStream = new FileOutputStream(file.getAbsolutePath());
                workbook.write(fileOutputStream);
            }
        } catch (Exception e) {
            System.out.println("此时的行是多少" + number);
            e.printStackTrace();
        } finally {
            // 注意关闭流
            IOUtils.closeQuietly(fileOutputStream);
        }
        long end = System.currentTimeMillis();
        System.out.println((double) (end - begin) / 1000);

    }

    private static Map<String, String> re(String stringCellValue) {
//        String testStr = "BC-Smartics v2.2 (updated version).xlsm:/3510/6e6ecaa8-3955-4604-a923-95fad53d2244_BC-Smartics v2.2 (updated version).xlsm; Cashflow Mismatch Simulation_Smartics (udated version).xlsx:/3510/92c34787-c9ad-4b89-974f-3e234e2a270d_Cashflow Mismatch Simulation_Smartics (udated version).xlsx; IMMD Smartics-2 Contract Variation Acknowledgement Letter.rar:/3510/b14e0e9d-fecb-4da8-a850-d1de9532b42a_IMMD Smartics-2 Contract Variation Acknowledgement Letter.rar; Salesforce approval.png:/3510/10735de6-0cbd-48f1-9076-15e0de8f90f2_Salesforce approval.png; BCF_IMMD Smartics-2 CV.xlsx:/3510/d73450eb-82ca-4fc6-ac0e-65f4a0a93eca_BCF_IMMD Smartics-2 CV.xlsx; Risk Assessment Questionnaire_IMMD Smartics-2 CV.docx:/3510/bf9a680e-7a03-4539-a6ac-168f3a2d77e1_Risk Assessment Questionnaire_IMMD Smartics-2 CV.docx; Re RA Solution offering endorsement for IMMD Smartics-2 Contract Variation.msg:/3510/9fcc3288-7f74-45ab-b72f-fb5be56f1bfa_Re RA Solution offering endorsement for IMMD Smartics-2 Contract Variation.msg; Re RA approval GTM endorsement for IMMD Smartics-2 Contract Variation.msg:/3510/75bb4dc9-2658-4750-a186-e0eabdb1e6ec_Re RA approval GTM endorsement for IMMD Smartics-2 Contract Variation.msg; 1a Award Letter - ImmD - SMARTICS-2 - 2017-03-17.pdf:/3510/57e710c0-63e5-44cf-9409-1a367b036768_1a Award Letter - ImmD - SMARTICS-2 - 2017-03-17.pdf; RE Entity approval IMMD Smartics-2 Contract Variation.msg:/3510/ea9f4345-3ca8-4fbc-b386-9415272756e9_RE Entity approval IMMD Smartics-2 Contract Variation.msg; Request for consent on using Solutions Limited as signing entity IMMD SMARTICS2 Contract Variation.docx:/3510/1f348f4f-f7d4-4f1f-a250-edf25c1f4cff_Request for consent on using Solutions Limited as signing entity IMMD SMARTICS2 Contract Variation.docx";
        String[] AttPaths = stringCellValue.split(";_;");
        Map<String, String> maps = new HashMap<>();
        for (String str : AttPaths) {
            String[] split = str.split(":_:");
            maps.put(split[1],split[0]);
        }
        return maps;
    }
}
