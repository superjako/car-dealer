package com.sg.util;

import com.sg.constant.SystemConstant;
import com.sg.exception.BusinessException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:SimpleExcelDataImportUtil <br/>
 * Function: excel数据读取工具<br/>
 * Date: 2017年3月6日 上午11:48:48 <br/>
 *
 * @author
 * @version
 * @since JDK 1.8
 * @see
 */
public class SimpleExcelDataImportUtil {

    /**
     *
     * readXlsx: office10读取<br/>
     *
     * @author
     * @param path
     * @return
     * @throws IOException
     * @since JDK 1.8
     */
    public static List<Row> readXlsx(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<Row> rowList = new ArrayList<Row>();
        // 获取Sheet
        int numberOfSheets = xssfWorkbook.getNumberOfSheets();
        if (numberOfSheets > 1) {
            numberOfSheets = 1;
        }
        for (int numSheet = 0; numSheet < numberOfSheets; numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 获取 Row
            int lastRownNum = xssfSheet.getLastRowNum();
            for (int rowNum = 2; rowNum <= lastRownNum; rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    rowList.add(xssfRow);
                }
            }
        }
        if (xssfWorkbook != null) {
            xssfWorkbook.close();
        }
        if (is != null) {
            is.close();
        }
        return rowList;
    }

    /**
     *
     * readXls: office03-07读取<br/>
     *
     * @author
     * @param path
     * @return
     * @throws IOException
     * @since JDK 1.8
     */
    public static List<Row> readXls(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<Row> rowList = new ArrayList<Row>();
        // 获取Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 获取Row
            for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    rowList.add(hssfRow);
                }
            }
        }
        if (hssfWorkbook != null) {
            hssfWorkbook.close();
        }
        if (is != null) {
            is.close();
        }
        return rowList;
    }

    /**
     * 检查excel文件
     * @param file 目标文件
     * @throws BusinessException 主动异常
     */
    public static void checkExcelFile(MultipartFile file) throws BusinessException {
        // 获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if(file.isEmpty()){
            throw new BusinessException("文件为空");
        }
        if (!SystemConstant.ExcelType.EXCEL_XLS.equals(suffix.toLowerCase()) && !SystemConstant.ExcelType.EXCEL_XLSX.equals(suffix.toLowerCase())){
            throw new BusinessException("文件不是excel文件");
        }
    }

    /**
     * 解析Excel文件
     * @param file 目标文件
     * @return 每一行值的集合的集合
     * @throws BusinessException 主动异常
     * @throws IOException IO流异常
     */
    public static List<List<Object>> readExcel(MultipartFile file) throws BusinessException, IOException {
        // 获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        //检查文件
        checkExcelFile(file);
        //根据后缀生成对应的workbook
        Workbook workbook;
        if (SystemConstant.ExcelType.EXCEL_XLS.equals(suffix.toLowerCase())){
            workbook = new HSSFWorkbook(file.getInputStream());
        }else if (SystemConstant.ExcelType.EXCEL_XLSX.equals(suffix.toLowerCase())){
            workbook = new XSSFWorkbook(file.getInputStream());
        }else {
            throw new BusinessException("文件格式不对");
        }
        // 仅获取第一个SHEET的内容
        Sheet sheet = workbook.getSheetAt(0);
        if (DataUtil.isEmpty(sheet)){
            throw new BusinessException("文件为空");
        }
        // 创建一个装所有行的内容的集合
        List<List<Object>> list = new ArrayList<>();
        // 跳过表头遍历每一行
        for (int i = 1; i <= sheet.getLastRowNum(); i++){
            // 创建一个装行的内容的集合
            List<Object> cellList = new ArrayList<>();
            Row row = sheet.getRow(i);
            for (int j = 0; j <= row.getLastCellNum(); j++) {
                // 如果值为空，则该位置存null值
                if (DataUtil.isEmpty(row.getCell(j))){
                    cellList.add(null);
                }else if ("".equals(row.getCell(j).toString().trim())){
                    cellList.add(null);
                }else {
                    row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                    cellList.add(getValue(row.getCell(j)));
                }
            }
            list.add(cellList);
        }
        return list;
    }

    /**
     * 根据单元格类型返回对应值
     * @param cell 单元格
     * @return 值
     */
    private static Object getValue(Cell cell) {
        Object obj = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_ERROR:
                obj = cell.getErrorCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                obj = cell.getNumericCellValue();
                break;
            case Cell.CELL_TYPE_STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }

}
