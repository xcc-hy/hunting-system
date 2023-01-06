package com.study.hunting.client;

import com.study.hunting.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "fileManagerSystem", path = "/fileManager")
public interface FileManagerClient {

    @PostMapping(value = "/secret/save/ico", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultVO<String> saveIco(@RequestPart("multipartFile")  MultipartFile multipartFile);

    @GetMapping("/ico/{format}/{icoName}")
    ResultVO<String> loadIco(@PathVariable("format") String format, @PathVariable("icoName") String icoName);

    @PostMapping(value = "/secret/save/resume", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultVO<String> saveResume(@RequestPart("multipartFile")  MultipartFile multipartFile);

    @GetMapping("/resume/{format}/{resumeName}")
    ResultVO<String> loadResume(@PathVariable("format") String format, @PathVariable("resumeName") String resumeName);

    @PostMapping(value = "/secret/save/license", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultVO<String> saveLicense(@RequestPart("multipartFile") MultipartFile multipartFile);

    @GetMapping("/license/{format}/{licenseName}")
    ResultVO<String> loadLicense(@PathVariable("format") String format, @PathVariable("licenseName") String licenseName);
}
