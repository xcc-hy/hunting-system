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
@TableName("tbl_new_company_application")
@ApiModel(value="NewCompanyApplication对象", description="")
public class NewCompanyApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "申请人id")
    private Integer applicationId;

    @ApiModelProperty(value = "申请状态（0，未审核；1，审核通过；2，审核不通过；3，存疑）")
    private String status;

    @ApiModelProperty(value = "新公司名称")
    private String companyName;

    @ApiModelProperty(value = "新公司全名")
    private String companyFullName;

    @ApiModelProperty(value = "新公司是否上市")
    private String companyIsListed;

    @ApiModelProperty(value = "新公司规模")
    private Integer companyScaleLevel;

    @ApiModelProperty(value = "新公司类型")
    private String companyType;

    @ApiModelProperty(value = "新公司法人")
    private String companyCorporate;

    @ApiModelProperty(value = "新公司注册资金")
    private Double companyRegisterCapital;

    @ApiModelProperty(value = "新公司成立时间")
    private String companyEstablishDate;

    @ApiModelProperty(value = "新公司行业id")
    private Integer companyIndustryId;

    @ApiModelProperty(value = "新公司介绍")
    private String companyIntroduction;

    @ApiModelProperty(value = "新公司营业执照位置")
    private String companyImgPath;

    @ApiModelProperty(value = "申请时间")
    private String applicationDate;

    @ApiModelProperty(value = "审核时间")
    private String auditDate;

    @ApiModelProperty(value = "审核人id")
    private Integer auditorId;

    @ApiModelProperty(value = "审核意见")
    private String reviewReason;

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
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }
    public String getCompanyIsListed() {
        return companyIsListed;
    }

    public void setCompanyIsListed(String companyIsListed) {
        this.companyIsListed = companyIsListed;
    }
    public Integer getCompanyScaleLevel() {
        return companyScaleLevel;
    }

    public void setCompanyScaleLevel(Integer companyScaleLevel) {
        this.companyScaleLevel = companyScaleLevel;
    }
    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
    public String getCompanyCorporate() {
        return companyCorporate;
    }

    public void setCompanyCorporate(String companyCorporate) {
        this.companyCorporate = companyCorporate;
    }
    public Double getCompanyRegisterCapital() {
        return companyRegisterCapital;
    }

    public void setCompanyRegisterCapital(Double companyRegisterCapital) {
        this.companyRegisterCapital = companyRegisterCapital;
    }
    public String getCompanyEstablishDate() {
        return companyEstablishDate;
    }

    public void setCompanyEstablishDate(String companyEstablishDate) {
        this.companyEstablishDate = companyEstablishDate;
    }
    public Integer getCompanyIndustryId() {
        return companyIndustryId;
    }

    public void setCompanyIndustryId(Integer companyIndustryId) {
        this.companyIndustryId = companyIndustryId;
    }
    public String getCompanyIntroduction() {
        return companyIntroduction;
    }

    public void setCompanyIntroduction(String companyIntroduction) {
        this.companyIntroduction = companyIntroduction;
    }
    public String getCompanyImgPath() {
        return companyImgPath;
    }

    public void setCompanyImgPath(String companyImgPath) {
        this.companyImgPath = companyImgPath;
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
    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    @Override
    public String toString() {
        return "NewCompanyApplication{" +
            "id=" + id +
            ", applicationId=" + applicationId +
            ", status=" + status +
            ", companyName=" + companyName +
            ", companyFullName=" + companyFullName +
            ", companyIsListed=" + companyIsListed +
            ", companyScaleLevel=" + companyScaleLevel +
            ", companyType=" + companyType +
            ", companyCorporate=" + companyCorporate +
            ", companyRegisterCapital=" + companyRegisterCapital +
            ", companyEstablishDate=" + companyEstablishDate +
            ", companyIndustryId=" + companyIndustryId +
            ", companyIntroduction=" + companyIntroduction +
            ", companyImgPath=" + companyImgPath +
            ", applicationDate=" + applicationDate +
            ", auditDate=" + auditDate +
            ", auditorId=" + auditorId +
            ", reviewReason=" + reviewReason +
        "}";
    }
}
