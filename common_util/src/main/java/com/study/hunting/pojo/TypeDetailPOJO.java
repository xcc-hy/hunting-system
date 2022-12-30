package com.study.hunting.pojo;

import java.util.HashMap;
import java.util.Map;

public class TypeDetailPOJO {
    private String name;
    public Map<Integer, String> detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeDetailPOJO() {
        detail = new HashMap<>();
    }

    public TypeDetailPOJO(String name) {
        this.name = name;
        detail = new HashMap<>();
    }
}
