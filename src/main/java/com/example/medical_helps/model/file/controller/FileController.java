package com.example.medical_helps.model.file.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 文件上传
 *
 * @author makejava
 * @since 2021-01-25 09:12:42
 */
@RestController
@RequestMapping("file")
public class FileController {

    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
       String filePath = "C://temp-rainy//"; // 上传后的路径
      //  String filePath = "/tmp/qztFile/"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String img_src="http://47.94.96.100:8088/image/"+fileName;
//        String filename = "/temp-rainy/" + fileName;
//        model.addAttribute("filename", filename);
        String img_src="http://localhost:8081/image/"+fileName;
        return img_src;
    }




}