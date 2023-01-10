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
@TableName("tbl_user_star")
@ApiModel(value="UserStar对象", description="")
public class UserStar implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer jobId;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserStar{" +
            "id=" + id +
            ", jobId=" + jobId +
            ", userId=" + userId +
        "}";
    }
}
