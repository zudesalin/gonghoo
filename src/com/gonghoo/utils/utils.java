package com.gonghoo.utils;

public class utils {
    /**
     * null转为空字符
     * <p/>
     * param str
     */
    public static String nullToSpace(String str) {
        if (str == null) {
            str = "";
        }
        return str;
    }

    public static Integer nullToInteger(String str) {
        return (str == null || "null".equals(str)) ? 0 : Integer.parseInt(str);
    }
}
