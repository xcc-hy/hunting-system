package com.study.hunting.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 职位的一级类别
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
@TableName("tbl_position_class")
@ApiModel(value="PositionClass对象", description="职位的一级类别")
public class PositionClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

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

    @Override
    public String toString() {
        return "PositionClass{" +
            "id=" + id +
            ", name=" + name +
        "}";
    }
}
