package com.sg.api;

import com.sg.bean.RestAPIResult;
import com.sg.util.FileUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

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

    @ApiOperation(value = "上传附件接口", notes = "上传附件接口")
    @RequestMapping(value = "/uploadFile", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
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

    private StringBuffer getFileSubstr(HttpServletRequest request) {
        StringBuffer sdf = new StringBuffer();
        sdf.append(uploadPath);
        return sdf;
    }
}
