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
@TableName("tbl_job")
@ApiModel(value="Job对象", description="")
public class Job implements Serializable {

//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }
//
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "创建者id")
    private Integer ownerId;

    @ApiModelProperty(value = "公司id")
    private Integer companyId;

    @ApiModelProperty(value = "产业类别id")
    private Integer industryId;

    @ApiModelProperty(value = "职位id")
    private Integer positionId;

    @ApiModelProperty(value = "地点id")
    private Integer locationId;

    @ApiModelProperty(value = "工作名字")
    private String name;

    @ApiModelProperty(value = "是否为实习")
    private String isPractice;

    @ApiModelProperty(value = "最低工资（千元）")
    private Double startPrice;

    @ApiModelProperty(value = "最高工资（千元）")
    private Double endPrice;

    @ApiModelProperty(value = "学历要求")
    private String education;

    @ApiModelProperty(value = "工作经验要求")
    private Integer workYear;

    @ApiModelProperty(value = "招收数量")
    private Integer requiredNum;

    @ApiModelProperty(value = "介绍")
    private String introduce;

    @ApiModelProperty(value = "状态,0,等待审核；1，通过审核；2，未通过审核；3，发起者冻结(通过审核)；4，管理员冻结")
    private String status;

    @ApiModelProperty(value = "投递人数")
    private Integer deliverNum;

    @ApiModelProperty(value = "面试人数")
    private Integer auditionNum;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "工作详细地址")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }
    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getIsPractice() {
        return isPractice;
    }

    public void setIsPractice(String isPractice) {
        this.isPractice = isPractice;
    }
    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }
    public Double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Double endPrice) {
        this.endPrice = endPrice;
    }
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
    public Integer getWorkYear() {
        return workYear;
    }

    public void setWorkYear(Integer workYear) {
        this.workYear = workYear;
    }
    public Integer getRequiredNum() {
        return requiredNum;
    }

    public void setRequiredNum(Integer requiredNum) {
        this.requiredNum = requiredNum;
    }
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getDeliverNum() {
        return deliverNum;
    }

    public void setDeliverNum(Integer deliverNum) {
        this.deliverNum = deliverNum;
    }
    public Integer getAuditionNum() {
        return auditionNum;
    }

    public void setAuditionNum(Integer auditionNum) {
        this.auditionNum = auditionNum;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Job{" +
            "id=" + id +
            ", ownerId=" + ownerId +
            ", companyId=" + companyId +
            ", industryId=" + industryId +
            ", positionId=" + positionId +
            ", locationId=" + locationId +
            ", name=" + name +
            ", isPractice=" + isPractice +
            ", startPrice=" + startPrice +
            ", endPrice=" + endPrice +
            ", education=" + education +
            ", workYear=" + workYear +
            ", requiredNum=" + requiredNum +
            ", introduce=" + introduce +
            ", status=" + status +
            ", deliverNum=" + deliverNum +
            ", auditionNum=" + auditionNum +
            ", createDate=" + createTime +
        "}";
    }
}
