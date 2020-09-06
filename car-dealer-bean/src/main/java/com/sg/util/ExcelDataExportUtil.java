package com.sg.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel工具类
 *
 * @Title: ExcelUtils
 * @Description:
 * @author:
 * @date 2019年8月22日 下午5:31:07
 */
public class ExcelDataExportUtil {

    /**
     * 导出单sheet数据
     *
     * @param fileName   文件名
     * @param sheetName  sheet名
     * @param headerList 表头
     * @param dataList   数据列表
     * @param response   响应
     * @throws IOException IO异常
     */
    public void writeXlsForSheet(String fileName, String sheetName, List<String> headerList, List<List<String>> dataList, HttpServletResponse response) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        OutputStream output = response.getOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setAlignment(HSSFCellStyle.BORDER_THIN);
        //创建sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //写入表头
        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headerList.size(); i++) {
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(headerList.get(i));
            sheet.setColumnWidth(i, 5000);
        }
        //写入数据
        for (int j = 0; j < dataList.size(); j++) {
            HSSFRow dataRow = sheet.createRow(j + 1);
            List<String> tempData = dataList.get(j);
            for (int k = 0; k < tempData.size(); k++) {
                HSSFCell cell = dataRow.createCell(k);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(tempData.get(k));
            }
        }
        workbook.write(output);
        output.flush();
        output.close();
    }

    /**
     * 导出两层表头单sheet数据
     *
     * @param fileName  文件名
     * @param sheetName sheet名称
     * @param head1     第一行表头
     * @param head2     第二行表头
     * @param headNum   第一行表头对应excel中的行和列
     * @param dataList  数据
     * @param response  响应
     * @throws IOException IO流异常
     */
    public void writeXlsForTwoHeaderSheet(String fileName, String sheetName, List<String> head1, List<String> head2, List<String> headNum, List<List<String>> dataList, HttpServletResponse response) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        OutputStream output = response.getOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setAlignment(HSSFCellStyle.BORDER_THIN);
        //创建sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //写入第一行表头
        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < head1.size(); i++) {
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(head1.get(i));
            sheet.setColumnWidth(i, 5000);
        }
        //动态合并单元格
        for (int i = 0; i < headNum.size(); i++) {
            String[] temp = headNum.get(i).split(",");
            Integer startrow = Integer.parseInt(temp[0]);
            Integer overrow = Integer.parseInt(temp[1]);
            Integer startcol = Integer.parseInt(temp[2]);
            Integer overcol = Integer.parseInt(temp[3]);
            sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
                    startcol, overcol));
        }
        //写入第二行表头
        HSSFRow headerRow2 = sheet.createRow(1);
        for (int i = 0; i < head2.size(); i++) {
            HSSFCell cell = headerRow2.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(head2.get(i));
            sheet.setColumnWidth(i, 5000);
        }
        //写入数据
        for (int j = 0; j < dataList.size(); j++) {
            HSSFRow dataRow = sheet.createRow(j + 2);
            List<String> tempData = dataList.get(j);
            for (int k = 0; k < tempData.size(); k++) {
                HSSFCell cell = dataRow.createCell(k);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(tempData.get(k));
            }
        }
        workbook.write(output);
        output.flush();
        output.close();
    }

    /**
     * * 导出Excel（根据列合并单元格）
     * * 2019/12/05 by sunpeng
     *
     * @param fileName
     * @param sheetName
     * @param headerList
     * @param dataList
     * @param mergeColumn           从第0列开始合并的列
     * @param extraMergeColumn      不连续的合并列（比如1,2列需要合并，第5列也需要跟着1,2列一起合并）,没有则传0
     * @param startMergeColumnIndex 额外合并列的开始列下标，没有则传0
     * @param response
     * @throws IOException
     */
    public void writeXlsForSheetMergeByColumn(String fileName, String sheetName, List<String> headerList, List<List<String>> dataList, int mergeColumn, int extraMergeColumn, int startMergeColumnIndex, HttpServletResponse response) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        OutputStream output = response.getOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setAlignment(HSSFCellStyle.BORDER_THIN);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        //创建sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //写入表头
        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headerList.size(); i++) {
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(headerList.get(i));
            sheet.setColumnWidth(i, 5000);
        }
        //写入数据
        for (int j = 0; j < dataList.size(); j++) {
            HSSFRow dataRow = sheet.createRow(j + 1);
            List<String> tempData = dataList.get(j);
            for (int k = 0; k < tempData.size(); k++) {
                HSSFCell cell = dataRow.createCell(k);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(tempData.get(k));
            }
        }
        mergeContent(sheet, mergeColumn, extraMergeColumn, startMergeColumnIndex);
        workbook.write(output);
        output.flush();
        output.close();
    }

    /**
     * 处理合并
     *
     * @param sheet
     * @param mergeColumn           从第0列开始合并的列
     * @param extraMergeColumn      不连续的合并列（比如1,2列需要合并，第5列也需要跟着1,2列一起合并）
     * @param startMergeColumnIndex 额外合并列的开始列下标
     */
    private void mergeContent(HSSFSheet sheet, int mergeColumn, int extraMergeColumn, int startMergeColumnIndex) {
        boolean flag = false;//前一行和后一行是否相同标志位
        int index = 0;
        int rownum = -1;
        int maxRowNum = sheet.getLastRowNum();

        // 循环导出的最大行数
        for (int i = 1; i < maxRowNum + 1; i++) {
            Map<Integer, Object> map1 = new HashMap<Integer, Object>();
            Map<Integer, Object> map2 = new HashMap<Integer, Object>();
            String frontRow = "";
            String afterRow = "";

            //最后一行就不需要判断和下一行是否相同了
            if (i != maxRowNum) {
                // 循环前面两列
                for (int j = 0; j < mergeColumn; j++) {
                    HSSFCell cell2 = sheet.getRow(i).getCell(j);//前一行
                    HSSFCell cell3 = sheet.getRow(i + 1).getCell(j);//后一行

                    frontRow = cell2.getStringCellValue().trim();
                    afterRow = cell3.getStringCellValue().trim();

                    map1.put(j, frontRow);
                    map2.put(j, afterRow);
                }
            }
            if (map1.equals(map2) && DataUtil.isNotEmpty(map1)) {
                // 相同的时候， 标记改成true
                flag = true;
                index++;//记录内容相同的行数
                if (rownum == -1) {
                    rownum = i;
                }
            } else {
                if (flag) {
                    //前几列合并
                    for (int k = 0; k < mergeColumn; k++) {
                        sheet.addMergedRegion(new CellRangeAddress(rownum, rownum + index, k, k));
                    }
                    if (extraMergeColumn != 0) {
                        int newStartMergeColumn = startMergeColumnIndex;
                        //间隔几列后的合并
                        for (int p = 0; p < extraMergeColumn; p++) {
                            sheet.addMergedRegion(new CellRangeAddress(rownum, rownum + index, newStartMergeColumn, newStartMergeColumn));
                            newStartMergeColumn++;
                        }
                    }
                    flag = false;
                    index = 0;
                    rownum = -1;
                }
            }
        }
    }
}

