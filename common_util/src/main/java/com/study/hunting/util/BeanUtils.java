package com.study.hunting.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class BeanUtils {

    public static <T, E extends T> E copyFromFather(T father, E child) throws Exception {
        Class<?> fatherClass = father.getClass();
        Field[] fatherField = fatherClass.getDeclaredFields();
        for (int i = 0; i < fatherField.length; i++) {
            Field field = fatherField[i];
            if (Modifier.isFinal(field.getModifiers())) continue;
            Method method = fatherClass.getDeclaredMethod("get" + upperFirstChar(field.getName()));
            field.setAccessible(true);
            field.set(child, method.invoke(father));
        }
        return child;
    }

    private static String upperFirstChar(String name) {
        String substring = name.substring(0, 1);
        return substring.toUpperCase() + name.substring(1);
    }
}
