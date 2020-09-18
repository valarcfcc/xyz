package com.valarcfcc.xyz.utils;

import com.valarcfcc.xyz.constant.GlobalConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtils {
    public static String excel2Sql (String filePath,Integer rowNum,Integer cellNum,String tableName,
                                    Integer pkNum,Integer columnNum,Integer typeNum ,Integer defaultNum,Integer nullNum,Integer commentNum){
        StringBuilder str = new StringBuilder();
        String column;
        String type;
        String pk;
        String comment;
        String defaultStr;
        String nullStr;
        HashMap<Integer,String> map;
        List<HashMap<Integer,String>> list = excel2MapList(filePath,rowNum,cellNum);
        if (list.isEmpty()){
            return "空";
        } else {
            str.append("CREATE TABLE ").append(tableName).append("( ");
            for (int i = 1; i < list.size(); i++) {
                map = list.get(i);
                column = map.get(columnNum);
                type = map.get(typeNum);
                pk = map.get(pkNum);
                defaultStr = map.get(defaultNum);
                nullStr = map.get(nullNum);
                str.append(column).append(" ").append(getType(type));
                if ("PK".equals(pk)){
                    str.append(" NOT NULL PRIMARY KEY");
                } else {
                    if (nullStr.contains("NOT")){
                        str.append("NOT NULL ");
                    }
                    if(!defaultStr.isEmpty()){
                        str.append(" DEFAULT '").append(defaultStr).append("'");
                    }
                }
                if (i < list.size()){
                    str.append(",");
                }
            }
            str.append(");");
            for (int i = 1; i < list.size(); i++) {
                map = list.get(i);
                column = map.get(columnNum);
                comment = map.get(commentNum);
                str.append("COMMENT ON COLUMN FACTOR.").append(tableName).append(".").append(column).append(" IS '").append(comment).append("';");
            }
            str.append("COMMIT;");
        }
        return str.toString();
    }
    private static final String DATE= "DATE";
    private static final String DB_DATE= "TIMESTAMP";
    private static final String NUMBER= "NUMBER";
    private static final String DB_NUMBER= "DECIMAL";
    private static final String CHAR= "CHAR";
    private static final String DB_CHAR= "CHARACTER";
    private static final String NVARCHAR2= "NVARCHAR2";
    private static final String DB_NVARCHAR2= "VARCHAR";
    private static String getType(String str){
        StringBuilder type = new StringBuilder();
        if (DATE.equals(str)){
            type.append(DB_DATE);
        }else if(str.contains(NUMBER)){
            int beginIndex = str.indexOf(GlobalConstants.Symbol.OPEN_PARENTHESIS);
            int endIndex = str.lastIndexOf(GlobalConstants.Symbol.CLOSE_PARENTHESIS);
            type.append(DB_NUMBER).append(str.substring(beginIndex,endIndex+1));
        } else if (str.contains(CHAR)){
            int beginIndex = str.indexOf(GlobalConstants.Symbol.OPEN_PARENTHESIS);
            int endIndex = str.lastIndexOf(GlobalConstants.Symbol.CLOSE_PARENTHESIS);
            type.append(DB_CHAR).append(str.substring(beginIndex,endIndex+1));
        }else if (str.contains(NVARCHAR2)){
            int beginIndex = str.indexOf(GlobalConstants.Symbol.OPEN_PARENTHESIS);
            int endIndex = str.lastIndexOf(GlobalConstants.Symbol.CLOSE_PARENTHESIS);
            type.append(DB_NVARCHAR2).append(str.substring(beginIndex,endIndex+1));
        }
        return type.toString();
    }


    public static List<HashMap<Integer,String>> excel2MapList (String filePath,Integer rowNum,Integer cellNum){
        Sheet sheet;
        Workbook workbook;
        List<HashMap<Integer,String>> list= new ArrayList<HashMap<Integer,String>>();
        try(InputStream input = new FileInputStream(filePath)){
            String excelType = filePath.substring(filePath.lastIndexOf(".") +1);
            if(excelType.equals("xls")){
                workbook = new HSSFWorkbook(input);
            }else if(excelType.equals("xlsx")){
                workbook = new XSSFWorkbook(input);
            }else {
                throw new Exception("文件类型错误!");
            }
            sheet = workbook.getSheetAt(0);
            int maxRow = sheet.getLastRowNum() +1;
            for (int i = 0;i < rowNum;i++){
                Row row = sheet.getRow(i);
                HashMap<Integer,String> map = new HashMap<>();
                for (int j = 0; j < cellNum; j++) {
                    map.put(j,getDataFromExcel(row,j));
                }
                list.add(map);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    private static String getDataFromExcel(Row row,Integer number) throws Exception {
        String val = "";
        Cell cell = row.getCell(number);
//        if(cell != null && !cell.toString().equals("")){
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
//        }else {
//            throw new Exception("数据为空！");
//        }
        return val;
    }
}
