package com.sg.api;

import com.baomidou.mybatisplus.extension.api.R;
import com.sg.bean.RestAPIResult;
import com.sg.util.FileUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description: 文件上传
 * @author: chenyong
 * @create: 2019-12-24 16:42
 **/
@Api(tags = "文件上传")
@RestController
@RequestMapping("/api/fileUpload")
public class FileUploadController {

    Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Value("${file.temp.path}")
    private String uploadPath;

    @ApiOperation(value = "上传文件接口", notes = "上传文件接口")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public RestAPIResult<Object> uploadFile(@ApiParam("文件") MultipartFile file, HttpServletRequest request){
        RestAPIResult<Object> result = new RestAPIResult<>();
        try {
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String file1 = getFileSubstr(request).append(fileUploadUtil.upload(fileName,inputStream)).toString();
            result.setRespData(file1);
        } catch (Exception e) {
            logger.error("fileupload error in method uploadFile" + e.getMessage());
        }
        return result;
    }
   /* @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public R uploadPicture(@ApiParam("图片") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        File targetFile = null;
        String url = "";//返回存储路径
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        if (fileName != null && fileName != "") {
            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/upload/imgs/";//存储路径
            String path = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName = System.currentTimeMillis() + "_" + new Random().nextInt(1000) + fileF;//新的文件名

            //先判断文件是否存在
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String fileAdd = sdf.format(new Date());
            //获取文件夹路径
            File file1 = new File(path + "/" + fileAdd);
            //如果文件夹不存在则创建
            if (!file1.exists() && !file1.isDirectory()) {
                file1.mkdir();
            }
            //将图片存入文件夹
            targetFile = new File(file1, fileName);
            try {
                //将上传的文件写到服务器上指定的文件。
                file.transferTo(targetFile);
                url = returnUrl + fileAdd + "/" + fileName;
                return R.ok(url);
            } catch (Exception e) {
                e.printStackTrace();
                return R.failed("系统异常，图片上传失败");
            }
        }
        return R.ok(null);
    }*/

    private StringBuffer getFileSubstr(HttpServletRequest request) {
        StringBuffer sdf = new StringBuffer();
        sdf.append(uploadPath);
        return sdf;
    }
}
