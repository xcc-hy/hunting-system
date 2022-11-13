package com.study.hunting.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    protected DateTimeUtils() {
    }

    public static String nowDateFormat() {
        return LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String nowTimeFormat() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static void main(String[] args) {
        System.out.println(DateTimeUtils.nowTimeFormat());
    }
}
