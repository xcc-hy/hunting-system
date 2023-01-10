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
 * @since 2023-01-07
 */
@TableName("tbl_education_experience")
@ApiModel(value="EducationExperience对象", description="")
public class EducationExperience implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "学校名称")
    private String schoolName;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "开始年份")
    private String startYear;

    @ApiModelProperty(value = "结束年份")
    private String endYear;

    @ApiModelProperty(value = "专业排名（1%,3%，5%，10%，15%，20%，30%，50%，80%）")
    private String majorRank;

    @ApiModelProperty(value = "介绍")
    private String introduction;

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
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }
    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }
    public String getMajorRank() {
        return majorRank;
    }

    public void setMajorRank(String majorRank) {
        this.majorRank = majorRank;
    }
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "EducationExperience{" +
            "id=" + id +
            ", userId=" + userId +
            ", schoolName=" + schoolName +
            ", education=" + education +
            ", major=" + major +
            ", startYear=" + startYear +
            ", endYear=" + endYear +
            ", majorRank=" + majorRank +
            ", introduction=" + introduction +
        "}";
    }
}
