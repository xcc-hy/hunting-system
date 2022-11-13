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
@TableName("tbl_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "姓名/昵称")
    private String name;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户类型")
    private String roleName;

    @ApiModelProperty(value = "冻结状态，0表示冻结，1表示正常状态")
    private String status;

    @ApiModelProperty(value = "头像位置")
    private String imgPath;

    @ApiModelProperty(value = "若用户为企业hr，则该项对应企业id")
    private Integer companyId;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "籍贯")
    private String hometown;

    @ApiModelProperty(value = "个人介绍")
    private String introduce;

    @ApiModelProperty(value = "个人技能")
    private String skill;

    @ApiModelProperty(value = "兴趣爱好")
    private String interest;

    @ApiModelProperty(value = "已沟通过的岗位数量")
    private Integer deliverNum;

    @ApiModelProperty(value = "收藏的岗位数量")
    private Integer starNum;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    public String getIsManager() {
        return isManager;
    }

    public void setIsManager(String isManager) {
        this.isManager = isManager;
    }

    @ApiModelProperty(value = "是否为公司管理者")
    private String isManager;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
    public Integer getDeliverNum() {
        return deliverNum;
    }

    public void setDeliverNum(Integer deliverNum) {
        this.deliverNum = deliverNum;
    }
    public Integer getStarNum() {
        return starNum;
    }

    public void setStarNum(Integer starNum) {
        this.starNum = starNum;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name=" + name +
            ", gender=" + gender +
            ", age=" + age +
            ", phone=" + phone +
            ", email=" + email +
            ", passward=" + password +
            ", roleName=" + roleName +
            ", status=" + status +
            ", imgPath=" + imgPath +
            ", companyId=" + companyId +
            ", education=" + education +
            ", hometown=" + hometown +
            ", introduce=" + introduce +
            ", skill=" + skill +
            ", interest=" + interest +
            ", deliverNum=" + deliverNum +
            ", starNum=" + starNum +
            ", createTime=" + createTime +
        "}";
    }
}
