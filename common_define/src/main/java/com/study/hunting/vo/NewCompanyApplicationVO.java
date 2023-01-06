package com.study.hunting.vo;

import com.study.hunting.domain.NewCompanyApplication;

public class NewCompanyApplicationVO extends NewCompanyApplication {

    private String applicationName;

    private String auditorName;

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
