package com.tongji.javaEE.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import com.tongji.javaEE.Service.*;
import com.tongji.javaEE.Domain.*;

import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {
    @Autowired
    private FileService fileService;

    @PostMapping("fileupload")
    @ResponseBody
    public WebFile upload(@RequestParam("file") MultipartFile file){
        WebFile webFile=new WebFile();
        String result=fileService.uploadFile(file);
        if(!result.equals("上传失败")){
            webFile.setStatus("上传成功");
            webFile.setFilepath(result);
        }
        else{
            webFile.setStatus("上传失败");
            webFile.setFilepath("wrong");
        }
        return webFile;
    }
}
