package com.gxa.controller;

import com.gxa.utils.FdfsUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping(value="/uploadfile")
    public String fileUpload(MultipartFile upload) throws Exception {
        if (upload.isEmpty()) {
            System.out.println("上传失败");
            return "fail";
        } else {
            System.out.println("开始上传");
            String fileName = upload.getOriginalFilename();
            System.out.println("fileName=" + fileName);
            String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
            System.out.println("extName=" + extName);
            String path = FdfsUtils.upload(upload.getBytes(), extName);
            System.out.println("path=" + path);

            return path;
        }
    }

}
