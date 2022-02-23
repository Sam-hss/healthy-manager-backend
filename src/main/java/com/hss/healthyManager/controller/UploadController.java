package com.hss.healthyManager.controller;

import com.hss.healthyManager.advice.ExceptionEnums;
import com.hss.healthyManager.advice.MyException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

@Slf4j
@Api(description = "文件上传接口")
@RestController
@RequestMapping(value = "api/uploadFile")
public class UploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadLocal(MultipartFile file) throws IOException {
        if (Objects.isNull(file)) {
            throw new MyException(ExceptionEnums.CHOOSE_FILE);
        } else {
            String fileName = file.getOriginalFilename();
            Integer index = fileName.lastIndexOf('.');
            String suffix = fileName.substring(index, fileName.length());
            System.out.println(suffix);
            String start = "";
            if ((".png").equals(suffix)) {
                start = "data:image/png;base64,";
            }
            if ((".jpg").equals(suffix) || (".jpeg").equals(suffix)) {
                start = "data:image/jpeg;base64,";
            }
            InputStream inputStream = file.getInputStream();
            OutputStream outputStream = new ByteArrayOutputStream();
            new BASE64Encoder().encodeBuffer(inputStream, outputStream);
            String key = outputStream.toString();
            return ResponseEntity.ok(start + key);
        }
    }
}
