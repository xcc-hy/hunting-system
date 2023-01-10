package com.study.hunting.vo;

import com.study.hunting.domain.Job;
import com.study.hunting.domain.JobUserRelation;
import com.study.hunting.domain.User;

public class JobUserRelationVO extends JobUserRelation {
    private Job job;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
