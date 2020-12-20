package com.tongji.javaEE.Service;
import com.tongji.javaEE.Dao.*;
import com.tongji.javaEE.Domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {
    public String uploadFile(MultipartFile file){
        String newname=Rename(file.getOriginalFilename());
        String path="C:/MyFiles/"+newname;
        File dest=new File(path);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try{
            file.transferTo(dest);
            return newname;
        }catch (IllegalStateException e){
            e.printStackTrace();
            return "上传失败";
        }catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }
    private static String Rename(String filename ){
        String first= UUID.randomUUID().toString().replace("-","");
        String last=filename.substring(filename.lastIndexOf("."));
        return first+last;
    }

}
