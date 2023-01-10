package com.study.hunting.controller;

import com.study.hunting.domain.EducationExperience;
import com.study.hunting.domain.ProjectExperience;
import com.study.hunting.domain.ResumeUserRelation;
import com.study.hunting.domain.WorkExperience;
import com.study.hunting.service.*;
import com.study.hunting.vo.ResultVO;
import com.study.hunting.vo.ResumeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/resume")
@Api("在线简历管理")
public class ResumeController {

    @Autowired
    private UserService userService;

    @Autowired
    private EducationExperienceService educationExperienceService;

    @Autowired
    private ProjectExperienceService projectExperienceService;

    @Autowired
    private WorkExperienceService workExperienceService;

    @Autowired
    private ResumeUserRelationService resumeUserRelationService;

    @GetMapping("/queryMine")
    @ApiOperation("查询自己的所有简历信息")
    public ResultVO<ResumeVO> queryMine(@RequestHeader Integer userId) {
        ResultVO<ResumeVO> result = new ResultVO<>();
        ResumeVO resumeVO = new ResumeVO();
        resumeVO.setResumes(resumeUserRelationService.queryByUserId(userId));
        resumeVO.setEducationExperiences(educationExperienceService.queryByUserId(userId));
        resumeVO.setProjectExperiences(projectExperienceService.queryByUserId(userId));
        resumeVO.setWorkExperiences(workExperienceService.queryByUserId(userId));
        result.setData(resumeVO);
        return result;
    }

    @GetMapping("/query/resume")
    @ApiOperation("查询自己的所有简历信息")
    public ResultVO<List<ResumeUserRelation>> queryResume(@RequestHeader Integer userId) {
        ResultVO<List<ResumeUserRelation>> result = new ResultVO<>();
        result.setData(resumeUserRelationService.queryByUserId(userId));
        return result;
    }

    @GetMapping("/queryById/{id}")
    @ApiOperation("查询用户简历信息")
    public ResultVO<ResumeVO> queryById(@PathVariable Integer id, Integer resumeId) {
        ResultVO<ResumeVO> result = new ResultVO<>();
        ResumeVO resumeVO = new ResumeVO();
        // 其他人不能访问简历附件，但是可以查看用户其他信息
        resumeVO.setUser(userService.queryById(id));
        resumeVO.setEducationExperiences(educationExperienceService.queryByUserId(id));
        resumeVO.setProjectExperiences(projectExperienceService.queryByUserId(id));
        resumeVO.setWorkExperiences(workExperienceService.queryByUserId(id));
        resumeVO.setResumes(resumeUserRelationService.queryById(resumeId));
        result.setData(resumeVO);
        return result;
    }

    @PostMapping("/education/create")
    @ApiOperation("新增教育经历并返回其id")
    public ResultVO<Integer> createEducation(@RequestHeader Integer userId, EducationExperience educationExperience) {
        return educationExperienceService.create(userId, educationExperience);
    }

    @PostMapping("/project/create")
    @ApiOperation("新增项目经历并返回其id")
    public ResultVO<Integer> createProject(@RequestHeader Integer userId, ProjectExperience projectExperience) {
        return projectExperienceService.create(userId, projectExperience);
    }

    @PostMapping("/work/create")
    @ApiOperation("新增工作经历并返回其id")
    public ResultVO<Integer> createWork(@RequestHeader Integer userId, WorkExperience workExperience) {
        return workExperienceService.create(userId, workExperience);
    }

    @PutMapping("/education/edit/{id}")
    @ApiOperation("修改教育经历")
    public ResultVO editEducation(@RequestHeader Integer userId, EducationExperience educationExperience, @PathVariable Integer id) {
        return educationExperienceService.edit(userId, educationExperience, id);
    }

    @PutMapping("/project/edit/{id}")
    @ApiOperation("修改项目经历")
    public ResultVO editProject(@RequestHeader Integer userId, ProjectExperience projectExperience, @PathVariable Integer id) {
        return projectExperienceService.edit(userId, projectExperience, id);
    }

    @PutMapping("/work/edit/{id}")
    @ApiOperation("修改工作经历")
    public ResultVO editWork(@RequestHeader Integer userId, WorkExperience workExperience, @PathVariable Integer id) {
        return workExperienceService.edit(userId, workExperience, id);
    }

    @DeleteMapping("/education/delete/{id}")
    @ApiOperation("删除教育经历")
    public ResultVO deleteEducation(@RequestHeader Integer userId, @PathVariable Integer id) {
        return educationExperienceService.deleteEducation(userId, id);
    }

    @DeleteMapping("/project/delete/{id}")
    @ApiOperation("删除项目经历")
    public ResultVO deleteProject(@RequestHeader Integer userId, @PathVariable Integer id) {
        return projectExperienceService.deleteProject(userId, id);
    }

    @DeleteMapping("/work/delete/{id}")
    @ApiOperation("删除工作经历")
    public ResultVO deleteWork(@RequestHeader Integer userId, @PathVariable Integer id) {
        return workExperienceService.deleteWork(userId, id);
    }

    @PostMapping("/resume/create")
    @ApiOperation("新增简历附件并返回")
    public ResultVO<ResumeUserRelation> createResume(@RequestHeader Integer userId, MultipartFile multipartFile) {
        return resumeUserRelationService.create(userId, multipartFile);
    }

    @DeleteMapping("/resume/delete/{id}")
    @ApiOperation("删除简历附件并返回")
    public ResultVO deleteResume(@RequestHeader Integer userId, @PathVariable Integer id) {
        return resumeUserRelationService.deleteResume(userId, id);
    }

}
