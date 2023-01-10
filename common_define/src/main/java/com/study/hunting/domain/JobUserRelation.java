package com.study.hunting.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author xcc
 * @since 2022-12-30
 */
@TableName("tbl_job_user_relation")
@ApiModel(value="JobUserRelation对象", description="")
public class JobUserRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer jobId;

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    @ApiModelProperty(value = "附件简历编号")
    private Integer resumeId;

    @ApiModelProperty(value = "流程，0，未进入；1，笔试流程；2，面试流程；3，流程通过；4，未通过")
    private String progress;

    @ApiModelProperty(value = "状态，0，等待hr审核；1，hr同意进入流程；2，hr拒绝进入流程")
    private String status;

    private String createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "JobUserRelation{" +
            "id=" + id +
            ", userId=" + userId +
            ", jobId=" + jobId +
            ", progress=" + progress +
            ", status=" + status +
            ", createDate=" + createDate +
        "}";
    }
}
