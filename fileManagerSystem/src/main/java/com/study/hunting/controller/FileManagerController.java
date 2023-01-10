package com.study.hunting.controller;

import com.study.hunting.service.FileManagerService;
import com.study.hunting.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/fileManager")
@Api("文件上传下载管理系统")
public class FileManagerController {

    @Autowired
    private FileManagerService fileManagerService;

    @PostMapping("/secret/save/ico")
    @ApiOperation("头像上传")
    public ResultVO<String> saveIco(MultipartFile multipartFile) throws IOException {
        return fileManagerService.saveIco(multipartFile);
    }

    @GetMapping("/ico/{format}/{icoName}")
    @ApiOperation("头像下载")
    public ResultVO<String> loadIco(@PathVariable String format, @PathVariable String icoName) throws IOException {
        return fileManagerService.loadIco(format, icoName);
    }

    @DeleteMapping("/secret/delete/ico/{format}/{name}")
    @ApiOperation("头像删除")
    public ResultVO deleteIco(@PathVariable String format, @PathVariable String name) throws IOException {
        return fileManagerService.deleteIco(format, name);
    }

    @PostMapping("/secret/save/resume")
    @ApiOperation("简历上传")
    public ResultVO<String> saveResume(MultipartFile multipartFile) throws IOException {
        return fileManagerService.saveResume(multipartFile);
    }

    @GetMapping("/resume/{format}/{resumeName}")
    @ApiOperation("简历下载")
    public ResultVO<String> loadResume(@PathVariable String format, @PathVariable String resumeName) throws IOException {
        return fileManagerService.loadResume(format, resumeName);
    }

    @DeleteMapping("/secret/delete/resume/{format}/{name}")
    @ApiOperation("简历删除")
    public ResultVO deleteResume(@PathVariable String format, @PathVariable String name) throws IOException {
        return fileManagerService.deleteResume(format, name);
    }

    @PostMapping("/secret/save/license")
    @ApiOperation("营业执照上传")
    public ResultVO<String> saveLicense(MultipartFile multipartFile) throws IOException {
        return fileManagerService.saveLicense(multipartFile);
    }

    @GetMapping("/license/{format}/{licenseName}")
    @ApiOperation("营业执照下载")
    public ResultVO<String> loadLicense(@PathVariable String format, @PathVariable String licenseName) throws IOException {
        return fileManagerService.loadLicense(format, licenseName);
    }

    @DeleteMapping("/secret/delete/license/{format}/{name}")
    @ApiOperation("营业执照删除")
    public ResultVO deleteLicense(@PathVariable String format, @PathVariable String name) throws IOException {
        return fileManagerService.deleteLicense(format, name);
    }
}
