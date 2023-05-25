package com.example.quan_ly_thu_chi.utils;

public class DataUtils {
    public static boolean isNullOrEmpty(final Object obj) {
        return obj == null || obj.toString().isEmpty();
    }
    public static boolean isNullOrEmptyOrBlank(final Object obj) {
        return obj == null || obj.toString().isEmpty() || obj.toString().isBlank();
    }
    public static boolean isEmptyOrBlank(final Object obj) {
        if (obj == null) return false;
        return obj.toString().isEmpty() || obj.toString().isBlank();
    }
}
