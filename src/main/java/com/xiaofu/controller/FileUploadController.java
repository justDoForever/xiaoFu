package com.xiaofu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.xiaofu.domain.enums.ResponseEnum;
import com.xiaofu.domain.response.ResultInfo;
import com.xiaofu.util.oss.OssClientFactory;
import com.xiaofu.util.oss.OssConfig;
import com.xiaofu.util.oss.OssUtil;

/**
 * @author Yang-o_o 2020-08-28 14:10
 */
@Api(value = "FileUploadController", tags = {"文件接口"})
@RestController
public class FileUploadController {

    @Autowired
    private OssClientFactory ossClientFactory;

    @Autowired
    private OssConfig ossConfig;

    @Value("${fileUrl}")
    private String fileUrl;

    @ApiOperation(value = "文件上传", notes = "文件上传", produces = "application/json")
    @PostMapping("/file/upload")
    public ResultInfo uploadWithName(@RequestParam("file") MultipartFile file,
                                     @RequestParam(defaultValue = "common") String folder) {
        String originalFilename = file.getOriginalFilename();
        String filePath = OssUtil.uploadFile(this.ossClientFactory.getInstance(),
                this.ossConfig.getBucketName(), folder, file);
        if (filePath==null){
            return new ResultInfo(ResponseEnum.FILE_UPLOAD_ERROR);
        }
        filePath = this.fileUrl + filePath;
        Map<String, Object> map = new HashMap<>(2);
        map.put("name", originalFilename);
        map.put("url", filePath);
        return new ResultInfo<>(ResponseEnum.SUCCESS, map);
    }

    @ApiOperation(value = "文件下载", notes = "文件下载", produces = "application/json")
    @GetMapping("/file/download")
    public void download(String filePath, HttpServletResponse response)
            throws IOException {
        String fileUrl = OssUtil.getFileUrl(this.ossClientFactory.getInstance(),
                this.ossConfig.getBucketName(), filePath);
        response.sendRedirect(fileUrl);
    }


}
