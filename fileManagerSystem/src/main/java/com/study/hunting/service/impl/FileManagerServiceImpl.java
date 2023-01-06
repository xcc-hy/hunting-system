package com.study.hunting.service.impl;

import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.FileManagerService;
import com.study.hunting.util.DateTimeUtils;
import com.study.hunting.util.MD5Utils;
import com.study.hunting.util.UUIDUtils;
import com.study.hunting.vo.ResultVO;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;

@Service
public class FileManagerServiceImpl implements FileManagerService {

    public String save(MultipartFile multipartFile, String baseName) throws IOException {
        String classPath = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
        String format = DateTimeUtils.nowDateFormat();
        String path = classPath + "static/" + baseName + "/" + format;
        File file = new File(path);
        if (!file.exists()) file.mkdirs();
        String newFileName = DigestUtils.md5DigestAsHex(multipartFile.getInputStream()); // 保证重复文件存为同一个位置
        file = new File(path, newFileName);
        if (!file.exists()) {
            multipartFile.transferTo(file); // 文件不存在则保存，存在则返回名字
        }
        return format + "/" + newFileName;
    }

    public byte[] load(String format, String fileName, String baseName) throws IOException {
        String classPath = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
        String path = classPath + "static/" + baseName + "/" + format + "/" + fileName;
        File file = new File(path);
        if (!file.exists()) return null;
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data = new byte[fileInputStream.available()];
        fileInputStream.read(data);
        fileInputStream.close();
        return data;
    }

    @Override
    public ResultVO<String> saveIco(MultipartFile multipartFile) throws IOException {
        ResultVO<String> result = new ResultVO<>();
        result.setData(save(multipartFile, "ico"));
        return result;
    }

    @Override
    public ResultVO<String> loadIco(String format, String icoName) throws IOException {
        ResultVO<String> result = new ResultVO<>();
        byte[] data = load(format, icoName, "ico");
        if (data == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        } else {
            result.setData(new String(Base64.getEncoder().encode(data)));
        }
        return result;
    }

    @Override
    public ResultVO<String> saveResume(MultipartFile multipartFile) throws IOException {
        ResultVO<String> result = new ResultVO<>();
        result.setData(save(multipartFile, "resume"));
        return result;
    }

    @Override
    public ResultVO<String> loadResume(String format, String resumeName) throws IOException {
        ResultVO<String> result = new ResultVO<>();
        byte[] data = load(format, resumeName, "resume");
        if (data == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        } else {
            result.setData(new String(data));
        }
        return result;
    }

    @Override
    public ResultVO<String> saveLicense(MultipartFile multipartFile) throws IOException {
        ResultVO<String> result = new ResultVO<>();
        result.setData(save(multipartFile, "license"));
        return result;
    }

    @Override
    public ResultVO<String> loadLicense(String format, String licenseName) throws IOException {
        ResultVO<String> result = new ResultVO<>();
        byte[] data = load(format, licenseName, "license");
        if (data == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        } else {
            result.setData(new String(Base64.getEncoder().encode(data)));
        }
        return result;
    }
}
