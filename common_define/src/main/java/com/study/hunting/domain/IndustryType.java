package com.study.hunting.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
@TableName("tbl_industry_type")
@ApiModel(value="IndustryType对象", description="")
public class IndustryType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Map<String, String> industryList;

    public Map<String, String> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(Map<String, String> industryList) {
        this.industryList = industryList;
    }

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
        return "IndustryType{" +
            "id=" + id +
            ", name=" + name +
        "}";
    }
}
