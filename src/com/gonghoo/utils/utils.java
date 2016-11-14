package com.gonghoo.utils;

/**
 * Created by DESKTOP-M874RE3$ on 2016/8/4.
 */
public class utils {
    /**
     * null转为空字符
     * @param str
     */
    public static String nullToSpace(String str){
        if(str==null){
            str="";
        }
        return str;
    }

    public static Integer nullToInteger(String str){
        return (str==null||"null".equals(str))?0:Integer.parseInt(str);
    }
}
