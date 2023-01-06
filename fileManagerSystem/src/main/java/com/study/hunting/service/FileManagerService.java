package com.study.hunting.service;

import com.study.hunting.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileManagerService {
    ResultVO<String> saveIco(MultipartFile multipartFile) throws IOException;

    ResultVO<String> loadIco(String format, String icoName) throws IOException;

    ResultVO<String> saveResume(MultipartFile multipartFile) throws IOException;

    ResultVO<String> loadResume(String format, String resumeName) throws IOException;

    ResultVO<String> saveLicense(MultipartFile multipartFile) throws IOException;

    ResultVO<String> loadLicense(String format, String licenseName) throws IOException;
}
