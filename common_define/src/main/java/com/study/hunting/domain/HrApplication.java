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
 * @since 2022-12-27
 */
@TableName("tbl_hr_application")
@ApiModel(value="HrApplication对象", description="")
public class HrApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "申请人id")
    private Integer applicationId;

    @ApiModelProperty(value = "申请状态，0：等待审核；1，通过；2，拒绝")
    private String status;

    @ApiModelProperty(value = "申请公司id")
    private Integer companyId;

    @ApiModelProperty(value = "申请时间")
    private String applicationTime;

    @ApiModelProperty(value = "审核时间")
    private String auditTime;

    @ApiModelProperty(value = "审核人id")
    private Integer auditorId;

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
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public String getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = applicationTime;
    }
    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }
    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    @Override
    public String toString() {
        return "HrApplication{" +
            "id=" + id +
            ", applicationId=" + applicationId +
            ", status=" + status +
            ", companyId=" + companyId +
            ", applicationDate=" + applicationTime +
            ", auditDate=" + auditTime +
            ", auditorId=" + auditorId +
        "}";
    }
}
