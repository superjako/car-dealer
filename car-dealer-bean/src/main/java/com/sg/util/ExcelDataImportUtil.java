package com.sg.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.sg.bean.RestAPIResult;
import com.sg.constant.SystemConstant;
import com.sg.exception.BusinessException;

/**
 * ClassName:ExcelDataImportUtil <br/>
 * Function: excel数据读取工具<br/>
 *
 * @author
 * @version
 * @since JDK 1.8
 * @see
 */
public class ExcelDataImportUtil {

    /**
     * 检查excel文件
     *
     * @param file 目标文件
     * @throws BusinessException 主动异常
     */
    public static void checkExcelFile (MultipartFile file) throws BusinessException {
        // 获取文件后缀
        String suffix = file.getOriginalFilename ().substring (file.getOriginalFilename ().lastIndexOf (".") + 1);
        if (file.isEmpty ()) {
            throw new BusinessException ("文件为空");
        }
        if (!SystemConstant.ExcelType.EXCEL_XLS.equals (suffix.toLowerCase ())
            && !SystemConstant.ExcelType.EXCEL_XLSX.equals (suffix.toLowerCase ())) {
            throw new BusinessException ("文件不是excel文件");
        }
    }

    /**
     * 解析Excel文件(返回错误信息)
     *
     * @param headNum 表头行数
     * @param keyList 表头的key名集合，对应该模块的实体
     * @param tableName 表名，用以校验模板是否符合，如明细表02
     * @param keyCHSList 表头中文
     * @param lengthList 该值允许的最大长度
     * @param typeList 该值对应类型（字符串string，数量int，金额bigdecimal，面积double,日期date）
     * @return 每一行值的集合的集合
     * @throws BusinessException 主动异常
     * @throws IOException IO流异常
     */
    public static RestAPIResult <List <Map <String, Object>>> importExcel (MultipartFile file, Integer headNum,
                                                                           List <String> keyList, String tableName,
                                                                           List <String> keyCHSList, List<Integer> lengthList,
                                                                           List<String> typeList) throws BusinessException,
                                                                                             IOException,
                                                                                             EncryptedDocumentException,
                                                                                             InvalidFormatException {
        // 检查文件
        ExcelDataImportUtil.checkExcelFile (file);
        RestAPIResult <List <Map <String, Object>>> result = new RestAPIResult<> ();
        InputStream is = file.getInputStream ();
        Workbook workbook = WorkbookFactory.create (is);
        Sheet sheet = workbook.getSheetAt (0);// 第一个sheet页
        // 仅获取第一个SHEET的内容
        if (DataUtil.isEmpty (sheet)) {
            throw new BusinessException ("文件为空");
        }
        // 判断是否对应的模板，根据模板编号名称判断
        if (tableName.equals (sheet.getRow (1).getCell (0))) {
            throw new BusinessException ("请上传正确的模板");
        }

        // 比较传入的集合数量是不是和模板一致
        int length = sheet.getRow (headNum).getLastCellNum () - 1;
        if (keyList.size () < length && keyList.size () != lengthList.size() || keyCHSList.size() != keyList.size() || typeList.size() != keyList.size()) {
            throw new BusinessException ("传入的key值集合不正确");
        }
        // 创建一个装所有行的内容的集合
        List <Map <String, Object>> list2 = new ArrayList<> ();
        // 创建一个装错误信息的集合
        List<String> msgList = new ArrayList<>();
        // 跳过表头遍历每一行
        for (int i = headNum; i <= sheet.getLastRowNum (); i++) {
            int nullCount = 1;
            // 创建一个装行的内容的集合
            Map <String, Object> rowMap = new LinkedHashMap <String, Object> ();
            Row row = sheet.getRow (i);
            if (row == null) {// 该行没有数据或者首个单元格不为数字，代表到已合计了，则不往下走
                continue;
            } else if (row.getCell (0).getCellType () != Cell.CELL_TYPE_NUMERIC) {
                break;
            }
            for (int j = 1; j < row.getLastCellNum (); j++) {
                Cell cell = row.getCell (j);
                // 如果值为空，则该位置存null值
                if (DataUtil.isEmpty (cell)) {
                    rowMap.put (keyList.get (j - 1), "");
                    nullCount++;
                } else if ("".equals (cell.toString ().trim ())) {
                    rowMap.put (keyList.get (j - 1), "");
                    nullCount++;
                } else {
                    cell.setCellType (Cell.CELL_TYPE_STRING);
                    int index = j - 1;
                    checkError(cell,lengthList,keyCHSList,typeList,index,i+1,j+1,msgList);
                    rowMap.put (keyList.get (index), getValue(cell));
                }
            }
            if (nullCount == row.getLastCellNum ()) {// 该行除了编号其余都为空
                continue;
            }
            list2.add (rowMap);
        }
        // 如果错误信息大于0，则只返回错误信息
        if (msgList.size() > 0){
            result.setRespCode(500);
            result.getRespMap().put("msg",msgList);
        }else {// 否则返回值
            result.setRespData(list2);
        }
        return result;
    }

    /**
     * 解析Excel文件
     *
     * @param headNum 表头行数
     * @param keyList 表头的key名集合，对应该模块的实体
     * @param tableName 表名，用以校验模板是否符合，如明细表02
     * @return 每一行值的集合的集合
     * @throws BusinessException 主动异常
     * @throws IOException IO流异常
     */
    public static RestAPIResult <List <Map <String, Object>>> importExcel (MultipartFile file, Integer headNum,
                                                                           List <String> keyList,
                                                                           String tableName) throws BusinessException,
            IOException,
            EncryptedDocumentException,
            InvalidFormatException {
        // 检查文件
        ExcelDataImportUtil.checkExcelFile (file);
        RestAPIResult <List <Map <String, Object>>> result = new RestAPIResult<> ();
        InputStream is = file.getInputStream ();
        Workbook workbook = WorkbookFactory.create (is);
        Sheet sheet = workbook.getSheetAt (0);// 第一个sheet页
        // 仅获取第一个SHEET的内容
        if (DataUtil.isEmpty (sheet)) {
            throw new BusinessException ("文件为空");
        }
        // 判断是否对应的模板，根据模板编号名称判断
        if (tableName.equals (sheet.getRow (1).getCell (0))) {
            throw new BusinessException ("请上传正确的模板");
        }

        // 比较传入的集合数量是不是和模板一致
        if (keyList.size () < sheet.getRow (headNum).getLastCellNum () - 1) {
            throw new BusinessException ("传入的key值集合不正确");
        }
        // 创建一个装所有行的内容的集合
        List <Map <String, Object>> list2 = new ArrayList<> ();
        // 跳过表头遍历每一行
        for (int i = headNum; i <= sheet.getLastRowNum (); i++) {
            int nullCount = 1;
            // 创建一个装行的内容的集合
            Map <String, Object> rowMap = new LinkedHashMap <String, Object> ();
            Row row = sheet.getRow (i);
            if (row == null) {// 该行没有数据或者首个单元格不为数字，代表到已合计了，则不往下走
                continue;
            } else if (row.getCell (0).getCellType () != Cell.CELL_TYPE_NUMERIC) {
                break;
            }
            for (int j = 1; j < row.getLastCellNum (); j++) {
                // 如果值为空，则该位置存null值
                if (DataUtil.isEmpty (row.getCell (j))) {
                    rowMap.put (keyList.get (j - 1), "");
                    nullCount++;
                } else if ("".equals (row.getCell (j).toString ().trim ())) {
                    rowMap.put (keyList.get (j - 1), "");
                    nullCount++;
                } else {
                    row.getCell (j).setCellType (Cell.CELL_TYPE_STRING);
                    rowMap.put (keyList.get (j - 1), ExcelDataImportUtil.getValue (row.getCell (j)));
                }
            }
            if (nullCount == row.getLastCellNum ()) {// 该行除了编号其余都为空
                continue;
            }
            list2.add (rowMap);
        }
        result.setRespData (list2);
        return result;
    }

    /**
     * 解析Excel文件（首列不是数字序号）
     *
     * @param headNum 表头行数
     * @param keyList 表头的key名集合，对应该模块的实体
     * @param tableName 表名，用以校验模板是否符合，如明细表02
     * @param contentNum 内容行数
     * @param notNeedList 不需要的列
     * @param keyCHSList 表头中文
     * @param lengthList 该值允许的最大长度
     * @param typeList 该值对应类型（字符串string，数量int，金额bigdecimal，面积double）
     * @return 每一行值的集合的集合
     * @throws BusinessException 主动异常
     * @throws IOException IO流异常
     */
    public static RestAPIResult <List <Map <String, Object>>> importExcel (MultipartFile file, Integer headNum,
                                                                           List <String> keyList, String tableName,
                                                                           Integer contentNum, List<Integer> notNeedList,
                                                                           List <String> keyCHSList, List<Integer> lengthList,
                                                                           List<String> typeList) throws BusinessException,
            IOException,
            EncryptedDocumentException,
            InvalidFormatException {
        RestAPIResult <List <Map <String, Object>>> result = new RestAPIResult<> ();
        InputStream is = file.getInputStream();
        Workbook workbook = WorkbookFactory.create (is);
        Sheet sheet = workbook.getSheetAt (0);// 第一个sheet页
        // 仅获取第一个SHEET的内容
        if (DataUtil.isEmpty (sheet)) {
            throw new BusinessException ("文件为空");
        }
        // 判断是否对应的模板，根据模板编号名称判断
        if (tableName.equals (sheet.getRow (1).getCell (0))) {
            throw new BusinessException ("请上传正确的模板");
        }
        if (keyList.size () != lengthList.size() || keyCHSList.size() != keyList.size() || typeList.size() != keyList.size()){
            throw new BusinessException("传入的key值集合不正确");
        }
        // 如果没有不需要的列，则正常比较
        if (DataUtil.isEmpty(notNeedList)) {
            // 比较传入的集合数量是不是和模板一致
            if (keyList.size() < sheet.getRow(headNum).getLastCellNum() - 2) {
                throw new BusinessException("传入的key值集合不正确");
            }
        }else {// 否则比较去掉不需要的列数后模板是否和传入key值集合一直
            if (keyList.size() < sheet.getRow(headNum).getLastCellNum() - 2 - notNeedList.size()) {
                throw new BusinessException("传入的key值集合不正确");
            }
        }
        // 创建一个装所有行的内容的集合
        List <Map <String, Object>> list2 = new ArrayList<> ();
        // 创建一个装错误信息的集合
        List<String> msgList = new ArrayList<>();
        // 跳过表头遍历每一行
        for (int i = headNum; i < contentNum + headNum; i++) {
            // 创建一个装行的内容的集合
            Map <String, Object> rowMap = new LinkedHashMap <> ();
            Row row = sheet.getRow (i);
            if (row == null) {
                continue;
            }
            // 已经忽略的列数
            int count = 0;
            for (int j = 2; j < row.getLastCellNum (); j++) {
                // 如果是不需要的列，则跳过
                if (DataUtil.isNotEmpty(notNeedList) && notNeedList.contains(j)){
                    count++;
                    continue;
                }
                Cell cell = row.getCell (j);
                // 如果值为空，则该位置存null值
                if (DataUtil.isEmpty (cell)) {
                    rowMap.put (keyList.get (j - 2 - count), "");
                } else if ("".equals (cell.toString ().trim ())) {
                    rowMap.put (keyList.get (j - 2 - count), "");
                } else {
                    cell.setCellType (Cell.CELL_TYPE_STRING);
                    int index = j - 2 - count;
                    checkError(cell,lengthList,keyCHSList,typeList,index,i+1,j+1,msgList);
                    rowMap.put (keyList.get (index), getValue (cell));
                }
            }
            list2.add (rowMap);
        }
        // 如果错误信息大于0，则只返回错误信息
        if (msgList.size() > 0){
            result.setRespCode(500);
            result.getRespMap().put("msg",msgList);
        }else {// 否则返回值
            result.setRespData(list2);
        }
        return result;
    }

    /**
     * 根据单元格类型返回对应值
     *
     * @param cell 单元格
     * @return 值
     */
    public static Object getValue (Cell cell) {
        Object obj = null;
        switch (cell.getCellType ()) {
        case Cell.CELL_TYPE_BOOLEAN:
            obj = cell.getBooleanCellValue ();
            break;
        case Cell.CELL_TYPE_ERROR:
            obj = cell.getErrorCellValue ();
            break;
        case Cell.CELL_TYPE_NUMERIC:
            obj = cell.getNumericCellValue ();
            break;
        case Cell.CELL_TYPE_STRING:
            obj = cell.getStringCellValue ();
            break;
        default:
            break;
        }
        return obj;
    }

    /**
     *
     * 验证是否是纯数字<br>
     *
     * @return 验证通过返回true，验证失败返回false
     */
    public static boolean isNumber (Cell cell, String cellName) {
        if (cell == null) {
            return false;
        } else {
            // 对单元格是数值类型做特殊处理
            if (cell.getCellType () == Cell.CELL_TYPE_NUMERIC) {
                String cellValue = String.valueOf (cell.getNumericCellValue ());
                cell.setCellType (Cell.CELL_TYPE_STRING);
                cell.setCellValue (cellValue);
            } else {
                cell.setCellType (Cell.CELL_TYPE_STRING);
            }
            String cellValue = trim (cell.getStringCellValue ());
            // 拿到最后小数点的下标
            int firstLen = cellValue.lastIndexOf (".");
            int secondLen = cellValue.length () - firstLen - 1;
            // 遍历最后小数点以后的内容是否为数字
            for (int i = firstLen; --i >= 0;) {
                if (!Character.isDigit ((cellValue.substring (0, cellValue.lastIndexOf ("."))).charAt (i))) {
                    return false;
                }
            }
            // 遍历最后小数点以前的内容是否为数字
            for (int i = secondLen; --i >= 0;) {
                if (!Character.isDigit ((cellValue.substring (cellValue.lastIndexOf (".") + 1,
                                                              cellValue.length ())).charAt (i))) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     *
     * 去除字符串中头部和尾部所包含的空格（包括:空格(全角，半角)）<br>
     *
     * @param s 字符串
     * @return 结果
     */
    public static String trim (String s) {
        if (DataUtil.isEmpty (s)) {
            return s;
        }
        s = s.trim ();
        while (s.charAt (0) == '　') { // 删除前部所有的全角空格
            s = s.substring (1);
        }
        while (s.charAt (s.length () - 1) == '　') { // 删除后部所有的全角空格
            s = s.substring (0, s.length () - 1);
        }
        return s;
    }

    /**
     * 验证参数是否能转换
     *
     * @param object
     * @return
     * @throws BusinessException
     */
    public final static boolean isInteger (Cell cell, String cellName) throws BusinessException {
        try {
            new BigDecimal (DataUtil.subEndString (cell.getStringCellValue ()));
            return true;
        } catch (Exception e) {
            throw new BusinessException (cellName + "：数据格式不对");
        }
    }

    /**
     * 检查错误
     * @param cell 单元格
     * @param lengthList 允许的最大长度
     * @param keyCHSList 中文表头
     * @param typeList 类型
     * @param index 集合下标
     * @param i 行号
     * @param j 列号
     * @param msgList 错误信息
     */
    public static void checkError(Cell cell, List<Integer> lengthList, List<String> keyCHSList, List<String> typeList, int index, int i, int j, List<String> msgList){
        StringBuffer sb = null;
        String value = String.valueOf(getValue(cell));
        // 判断长度
        if (value.length() > lengthList.get(index)){
            sb = new StringBuffer("该文件第" + i + "行，第" + j + "列（" + keyCHSList.get(index) +"）的长度超出限制（不超过" + lengthList.get(index) + ")");
        }
        try{
            // 判断类型
            if ("bigdecimal".equals(typeList.get(index).toLowerCase())){
                new BigDecimal(value);
            }else if ("double".equals(typeList.get(index).toLowerCase())){
                Double.parseDouble(value);
            }else if ("int".equals(typeList.get(index).toLowerCase())){
                Integer.valueOf(value);
            }
        }catch(Exception e){
            if (DataUtil.isEmpty(sb)){
                sb = new StringBuffer("该文件第" + i + "行，第" + j + "列（" + keyCHSList.get(index) +"）的内容错误，应为数字");
            }else {
                sb.append(";该文件第").append(i).append("行，第").append(j).append("列（").append(keyCHSList.get(index)).append("）的内容错误，应为数字");
            }
        }
        try{
            if ("date".equals(typeList.get(index).toLowerCase())){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                format.setLenient(false);
                format.parse(value);
            }
        }catch(Exception e){
            if (DataUtil.isEmpty(sb)){
                sb = new StringBuffer("该文件第" + i + "行，第" + j + "列（" + keyCHSList.get(index) +"）的日期错误，应为yyyy-MM-dd格式。例如：2020-01-01");
            }else {
                sb.append(";该文件第").append(i).append("行，第").append(j).append("列（").append(keyCHSList.get(index)).append("）的日期错误，应为yyyy-MM-dd格式。例如：2020-01-01");
            }
        }
        // 如果错误信息不为空，则存入集合
        if (DataUtil.isNotEmpty(sb)){
            msgList.add(sb.toString());
        }
    }

}
