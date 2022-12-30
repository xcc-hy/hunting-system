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

    @ApiModelProperty(value = "申请状态")
    private String status;

    @ApiModelProperty(value = "申请公司id")
    private Integer companyId;

    @ApiModelProperty(value = "申请时间")
    private String applicationDate;

    @ApiModelProperty(value = "审核时间")
    private String auditDate;

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
    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }
    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
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
            ", applicationDate=" + applicationDate +
            ", auditDate=" + auditDate +
            ", auditorId=" + auditorId +
        "}";
    }
}
