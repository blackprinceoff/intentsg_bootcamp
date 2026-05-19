package com.tasks.util;

import java.lang.reflect.Method;

import com.tasks.annotation.DefaultArea;

public class AnnotationScanner {
    private AnnotationScanner() {
    }

    public static void scanDefaultAreas(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(DefaultArea.class)) {
                    System.out.println("Method " + method.getName() + " in class " + clazz.getSimpleName()
                            + " is marked as @DefaultArea");
                }
            }
        }
    }

}
