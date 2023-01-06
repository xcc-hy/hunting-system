package com.study.hunting.vo;

import com.study.hunting.domain.NewJobApplication;

public class NewJobApplicationVO extends NewJobApplication {

    public String getAuditName() {
        return auditName;
    }
    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    private String auditName;
}
