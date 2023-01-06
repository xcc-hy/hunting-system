package com.study.hunting.util;

import java.util.UUID;

public class UUIDUtils {

    public static String getUUID(){

        return UUID.randomUUID().toString().replaceAll("-","");

    }

    public static void main(String[] args) {
        System.out.println(UUIDUtils.getUUID());
    }
}
