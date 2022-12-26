package com.study.hunting.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 全国城市表
 * </p>
 *
 * @author xcc
 * @since 2022-12-26
 */
@TableName("tbl_city")
@ApiModel(value="City对象", description="全国城市表")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关键字")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    private Integer pid;

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
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "City{" +
            "id=" + id +
            ", name=" + name +
            ", pid=" + pid +
        "}";
    }
}
