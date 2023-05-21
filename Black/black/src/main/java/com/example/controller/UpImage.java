package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UpImage {
    @PostMapping("/upimage")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile fileUpload){
        Map<String,Object> map = new HashMap<>();
        String fileName=fileUpload.getOriginalFilename();
        String sufficName=fileName.substring(fileName.lastIndexOf("."));
        fileName= UUID.randomUUID()+sufficName;
        String filePath="/www/store/StaticData/";
        try{
            fileUpload.transferTo(new File(filePath+fileName));
            map.put("url","http://36.133.127.248:8880/StaticData/"+fileName);
            map.put("code",1);
            return map;
        }catch(IOException e){
            e.printStackTrace();
            map.put("code",-1);
            return map;
        }
    }

}
