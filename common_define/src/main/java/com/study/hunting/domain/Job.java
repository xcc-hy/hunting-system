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
 * @since 2022-10-02
 */
@TableName("tbl_job")
@ApiModel(value="Job对象", description="")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer ownerId;

    private Integer positionId;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "是否为实习")
    private String isPractice;

    @ApiModelProperty(value = "最低薪资")
    private Double startPrice;

    @ApiModelProperty(value = "最高薪资")
    private Double endPrice;

    @ApiModelProperty(value = "学历要求")
    private String education;

    @ApiModelProperty(value = "工作经历要求")
    private Integer workYear;

    @ApiModelProperty(value = "招收人数")
    private Integer requiredNum;

    @ApiModelProperty(value = "工作介绍")
    private String introduce;

    @ApiModelProperty(value = "当前状态（正在招聘、招聘结束、、、）")
    private String status;

    @ApiModelProperty(value = "投递简历人数")
    private Integer deliverNum;

    @ApiModelProperty(value = "面试人数")
    private Integer auditionNum;

    private String createTime;

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
    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
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
            ", positionId=" + positionId +
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
            ", createTime=" + createTime +
        "}";
    }
}
