package com.study.hunting.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 
 * </p>
 *
 * @author xcc
 * @since 2022-12-30
 */
@TableName("tbl_new_job_application")
@ApiModel(value="NewJobApplication对象", description="")
public class NewJobApplication implements Serializable {

//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }
//
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer applicationId;

    private Integer jobId;

    private String applicationTime;

    private Integer auditId;

    private String auditTime;

    private String reviewReason;

    @ApiModelProperty("该审核结果：0，未审核；1，通过；2，未通过；4，管理员冻结")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }
    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
    public String getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = applicationTime;
    }
    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }
    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }
    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    @Override
    public String toString() {
        return "NewJobApplication{" +
            "id=" + id +
            ", applicationId=" + applicationId +
            ", jobId=" + jobId +
            ", applicationDate=" + applicationTime +
            ", auditId=" + auditId +
            ", auditDate=" + auditTime +
            ", reviewReason=" + reviewReason +
        "}";
    }
}
