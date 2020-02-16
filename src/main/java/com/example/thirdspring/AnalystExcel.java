package com.example.thirdspring;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;

public class AnalystExcel {
    private final static String excel2003L = ".xls";
    private final static String excel2007U = ".xlsx";
    private  InputStream in;
    private String fileName;
    public  String mark;
    public String name;
    public int id;
    AnalystExcel(InputStream in, String fileName) {
        this.in=in;
        this.fileName=fileName;
    }

    public void BankListByExcel(InputStream in,String fileName) throws Exception {
        //List<List<Object>> list = null;
        Workbook work = this.getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作簿为空");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        //list = new ArrayList<List<Object>>();
        importSql imSql=new importSql();
        importSql.connect();
        for (int i = 0; i < work.getNumberOfSheets(); ++i) {
            sheet = work.getSheetAt(i);
            if (null == sheet) {
                continue;
            }
            for (int j = sheet.getFirstRowNum()+1; j <= sheet.getLastRowNum(); ++j) {
                row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                //遍历所有的列
                //List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); ++y) {
                    cell = row.getCell(y);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (y == 0) {
                        mark=cell.getStringCellValue();
                        //System.out.println(cell.getStringCellValue());
                    }
                    else
                        name=cell.getStringCellValue();
                }
                imSql.insert(mark,name);
            }
        }
    }

    /**

     * @Description:根据文件后缀，自适应上传文件的版本

     * @auther:liang.ma

     * @param fileName

     * @param inStr

     * @throws Exception

     * @time:2017年9月13日下午4:38:21

     */
    private Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("解析的文件格式有误");
        }
        return wb;
    }
}
