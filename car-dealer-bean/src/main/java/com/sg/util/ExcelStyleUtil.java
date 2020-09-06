package com.sg.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 * excel导出样式
 */
public class ExcelStyleUtil {

    //表头字体
    public static CellStyle headerStyle(HSSFWorkbook wb){
        //表头字体
        Font headerFont = wb.createFont();
        headerFont.setFontName("微软雅黑");
        headerFont.setFontHeightInPoints((short) 18);
        headerFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        headerFont.setColor(HSSFColor.BLACK.index);

        //表头样式，左右上下居中
        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        headerStyle.setLocked(true);
        headerStyle.setWrapText(false);// 自动换行
        return headerStyle;
    }

    private static Font contextFont(HSSFWorkbook wb){
        //正文字体
        Font contextFont = wb.createFont();
        contextFont.setFontName("微软雅黑");
        contextFont.setFontHeightInPoints((short) 12);
        contextFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        contextFont.setColor(HSSFColor.BLACK.index);
        return contextFont;
    }

    //单元格样式，左右上下居中 边框
    public static CellStyle commonStyle(HSSFWorkbook wb){
        //单元格样式，左右上下居中 边框
        CellStyle commonStyle = wb.createCellStyle();
        commonStyle.setFont(contextFont(wb));
        commonStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        commonStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        commonStyle.setLocked(true);
        commonStyle.setWrapText(true);// 自动换行
        commonStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        commonStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        commonStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        commonStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        return commonStyle;
    }

    //单元格样式，左右上下居中 边框
    public static CellStyle commonWrapStyle(HSSFWorkbook wb){
        //单元格样式，左右上下居中 边框
        CellStyle commonWrapStyle = wb.createCellStyle();
        commonWrapStyle.setFont(contextFont(wb));
        commonWrapStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        commonWrapStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        commonWrapStyle.setLocked(true);
        commonWrapStyle.setWrapText(true);// 自动换行
        commonWrapStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        commonWrapStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        commonWrapStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        commonWrapStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        return commonWrapStyle;
    }

    //单元格样式，竖向 边框
    public static CellStyle verticalStyle(HSSFWorkbook wb){
        //单元格样式，竖向 边框
        CellStyle verticalStyle = wb.createCellStyle();
        verticalStyle.setFont(contextFont(wb));
        verticalStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        verticalStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        verticalStyle.setRotation((short) 255);//竖向
        verticalStyle.setLocked(true);
        verticalStyle.setWrapText(false);// 自动换行
        verticalStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        verticalStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        verticalStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        verticalStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        return verticalStyle;
    }

    //单元格样式，左右上下居中 无边框
    public static CellStyle commonStyleNoBorder(HSSFWorkbook wb){
        //单元格样式，左右上下居中 无边框
        CellStyle commonStyleNoBorder = wb.createCellStyle();
        commonStyleNoBorder.setFont(contextFont(wb));
        commonStyleNoBorder.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        commonStyleNoBorder.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        commonStyleNoBorder.setLocked(true);
        commonStyleNoBorder.setWrapText(false);// 自动换行
        return commonStyleNoBorder;
    }

    //单元格样式，左对齐 边框
    public static CellStyle alignLeftStyle(HSSFWorkbook wb){
        //单元格样式，左对齐 边框
        CellStyle alignLeftStyle = wb.createCellStyle();
        alignLeftStyle.setFont(contextFont(wb));
        alignLeftStyle.setAlignment(CellStyle.ALIGN_LEFT);// 左对齐
        alignLeftStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        alignLeftStyle.setLocked(true);
        alignLeftStyle.setWrapText(false);// 自动换行
        alignLeftStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        alignLeftStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        alignLeftStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        alignLeftStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        return alignLeftStyle;
    }

    //单元格样式，左对齐 无边框
    public static CellStyle alignLeftNoBorderStyle(HSSFWorkbook wb){
        //单元格样式，左对齐 无边框
        CellStyle alignLeftNoBorderStyle = wb.createCellStyle();
        alignLeftNoBorderStyle.setFont(contextFont(wb));
        alignLeftNoBorderStyle.setAlignment(CellStyle.ALIGN_LEFT);// 左对齐
        alignLeftNoBorderStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        alignLeftNoBorderStyle.setLocked(true);
        alignLeftNoBorderStyle.setWrapText(false);// 自动换行
        alignLeftNoBorderStyle.setAlignment(CellStyle.ALIGN_LEFT);// 左对齐
        return alignLeftNoBorderStyle;
    }

    //单元格样式，右对齐
    public static CellStyle alignRightStyle(HSSFWorkbook wb){
        //单元格样式，右对齐
        CellStyle alignRightStyle = wb.createCellStyle();
        alignRightStyle.setFont(contextFont(wb));
        alignRightStyle.setAlignment(CellStyle.ALIGN_LEFT);// 左对齐
        alignRightStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        alignRightStyle.setLocked(true);
        alignRightStyle.setWrapText(false);// 自动换行
        alignRightStyle.setAlignment(CellStyle.ALIGN_RIGHT);// 左对齐
        alignRightStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        alignRightStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        alignRightStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        alignRightStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        return alignRightStyle;
    }

    //单元格样式，左对齐 边框 居上
    public static CellStyle alignLeftUpStyle(HSSFWorkbook wb){
        //单元格样式，左对齐 边框
        CellStyle alignLeftUpStyle = wb.createCellStyle();
        alignLeftUpStyle.setFont(contextFont(wb));
        alignLeftUpStyle.setAlignment(CellStyle.ALIGN_LEFT);// 左对齐
        alignLeftUpStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);// 上下居中
        alignLeftUpStyle.setLocked(true);
        alignLeftUpStyle.setWrapText(false);// 自动换行
        alignLeftUpStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        alignLeftUpStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        alignLeftUpStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        alignLeftUpStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        return alignLeftUpStyle;
    }
}
