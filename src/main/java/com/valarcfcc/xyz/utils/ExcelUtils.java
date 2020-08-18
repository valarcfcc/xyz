package com.valarcfcc.xyz.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtils {
    public static ArrayList<HashMap<Integer,String>> excel2MapList (String filePath,Integer rowNum,Integer cellNum){
        Sheet sheet;
        Workbook workbook;
        List<Map<Integer,String>> list= new ArrayList<>();
        try(InputStream input = new FileInputStream(filePath)){
            String excelType = filePath.substring(filePath.lastIndexOf(".") +1);
            if(excelType.equals("xls")){
                workbook = new HSSFWorkbook(input);
            }else if(excelType.equals("xlsx")){
                workbook = new XSSFWorkbook(input);
            }else {
                throw new Exception("文件类型错误!");
            }

        }catch (Exception e){

        }
        return null;
    }

    private static String getDataFromExcel(Row row,Integer number) throws Exception {
        String val = "";
        Cell cell = row.getCell(number);
        if(cell != null && !cell.toString().equals("")){
            switch (cell.getCellType()){
                case Cell.CELL_TYPE_STRING:
                    val = cell.getStringCellValue();
                    break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        Boolean flag = cell.getBooleanCellValue();
                        val = flag.toString();
                        break;
                        case Cell.CELL_TYPE_NUMERIC:
                            if(DateUtil.isCellDateFormatted(cell)){
                                Date date = cell.getDateCellValue();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                val = format.format(date);
                            }else {
                                DecimalFormat format = new DecimalFormat("#.##");
                                val=format.format(cell.getNumericCellValue());
                            }
                            break;
                            case Cell.CELL_TYPE_BLANK:
                                break;
                default:
                    throw new Exception("数据类型不匹配");
            }
        }else {
            throw new Exception("数据为空！");
        }
        return val;
    }
}
