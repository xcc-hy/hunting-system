package com.study.hunting.vo;

import com.study.hunting.domain.*;

import java.io.Serializable;
import java.util.List;

public class ResumeVO implements Serializable {

    private User user;

    private List<ResumeUserRelation> resumes;

    private List<EducationExperience> educationExperiences;

    private List<ProjectExperience> projectExperiences;

    private List<WorkExperience> workExperiences;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ResumeUserRelation> getResumes() {
        return resumes;
    }

    public void setResumes(List<ResumeUserRelation> resumes) {
        this.resumes = resumes;
    }

    public List<EducationExperience> getEducationExperiences() {
        return educationExperiences;
    }

    public void setEducationExperiences(List<EducationExperience> educationExperiences) {
        this.educationExperiences = educationExperiences;
    }

    public List<ProjectExperience> getProjectExperiences() {
        return projectExperiences;
    }

    public void setProjectExperiences(List<ProjectExperience> projectExperiences) {
        this.projectExperiences = projectExperiences;
    }

    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }
}
