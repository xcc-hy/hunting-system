package com.study.hunting.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
@TableName("tbl_company")
@ApiModel(value="Company对象", description="")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "公司简称")
    private String name;

    @ApiModelProperty(value = "公司全称")
    private String fullName;

    @ApiModelProperty(value = "是否为上市公司，0，不是，1，是")
    private String isListed;

    @ApiModelProperty(value = "公司规模等级，10，100，1000，10000")
    private Integer scaleLevel;

    @ApiModelProperty(value = "公司类型")
    private String type;

    @ApiModelProperty(value = "公司介绍")
    private String introduction;

    @ApiModelProperty(value = "公司注册资金（万元）")
    private Double registerCapital;

    @ApiModelProperty(value = "公司法人")
    private String corporate;

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @ApiModelProperty(value = "注册人id")
    private Integer ownerId;

    @ApiModelProperty(value = "公司成立时间")
    private String establishDate;

    @ApiModelProperty(value = "营业执照路径")
    private String imgPath;

    @ApiModelProperty(value = "行业id")
    private Integer industryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getIsListed() {
        return isListed;
    }

    public void setIsListed(String isListed) {
        this.isListed = isListed;
    }
    public Integer getScaleLevel() {
        return scaleLevel;
    }

    public void setScaleLevel(Integer scaleLevel) {
        this.scaleLevel = scaleLevel;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public Double getRegisterCapital() {
        return registerCapital;
    }

    public void setRegisterCapital(Double registerCapital) {
        this.registerCapital = registerCapital;
    }
    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate;
    }
    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    @Override
    public String toString() {
        return "Company{" +
            "id=" + id +
            ", name=" + name +
            ", fullName=" + fullName +
            ", isListed=" + isListed +
            ", scaleLevel=" + scaleLevel +
            ", type=" + type +
            ", introduction=" + introduction +
            ", registerCapital=" + registerCapital +
            ", corporate=" + corporate +
            ", establishDate=" + establishDate +
            ", imgPath=" + imgPath +
            ", industryId=" + industryId +
        "}";
    }
}
