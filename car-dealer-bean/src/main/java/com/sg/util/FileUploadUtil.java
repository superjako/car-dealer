package com.sg.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileUploadUtil {

    @Value("${file.temp.path}")
    private String uploadPath;

    /**
     * 执行文件上传
     *
     * @param fileName 文件全名
     * @param inStream 待上传的文件流
     * @return 文件上传后的全路径
     */
    public String upload(String fileName, InputStream inStream) throws IOException {
        String fileNameWithoutExtension = getFileNameWithoutExtension(fileName);
        String fileExtensionName = getFileExtensionName(fileName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStr = sdf.format(new Date());
        String uploadFileName = fileNameWithoutExtension + timeStr + "." + fileExtensionName;
        File tempFile = new File(uploadPath, uploadFileName);
        // 根据指定的这个文件,来创建一个文件字节输出流(用于向磁盘写文件)
        FileOutputStream outputFile = new FileOutputStream(tempFile);
        // 实例一个字节数组初始为1000kb
        byte b[] = new byte[1000];
        int n;
        // 利用while循环,先读出这个文件大小,判断为不为-1,如果不为-1就输出数据,向磁盘写文件数据
        while ((n = inStream.read(b)) != -1) {
            outputFile.write(b, 0, n); // 将 n 个字节从b字节数组写入到文件
        }
        // 关闭文件输出流
        outputFile.close();
        // 关闭文件输入流
        inStream.close();
        return uploadFileName;
    }

    /**
     * 获取不带扩展名的文件名
     *
     * @param fileName 文件全名
     * @return 不带扩展名的文件名
     */
    public String getFileNameWithoutExtension(String fileName) {
        if (!StringUtils.isEmpty(fileName) && fileName.length() > 0) {
            int dot = fileName.lastIndexOf(".");
            if ((dot > -1) && (dot < (fileName.length()))) {
                return fileName.substring(0, dot);
            }
        }
        return "";
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件全名
     * @return 文件扩展名
     */
    public String getFileExtensionName(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                return fileName.substring(dot + 1);
            }
        }
        return fileName;
    }

    /**
     * 图片/文件/视频删除
     *
     * @param fileUrl
     */
    public void deleteFile(String fileUrl) {
        File tempFile = new File(uploadPath, fileUrl);
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    /**
     * 文件下载
     *
     * @param fileUrl
     * @param fileName
     * @param request
     * @param response
     * @throws IOException
     */
    public void downloadFile(String fileUrl, String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File(uploadPath, fileUrl);
        // 取得文件名。
        String filename = file.getName();
        // 取得文件的后缀名。
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
    }

    public void downloadFile(String fileUrl, String fileName, HttpServletResponse response) throws IOException {
        File file = new File(fileUrl);
        // 取得文件名。
        String filename = file.getName();
        // 取得文件的后缀名。
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
    }

    /*
     * @MethodName: getFileUrl
     * @Description: 获取模板文件地址
     * @Param: [fileName]
     * @Return: java.lang.String
     * @Author: Zheng.Zeng
     * @Date: 下午 5:07
     **/
    public String getTemplateUrl(String fileName) {
        return uploadPath + "\\" + fileName + ".xls";
    }

    ;

}
