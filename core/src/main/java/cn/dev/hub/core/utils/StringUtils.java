package cn.dev.hub.core.utils;

import java.io.StringWriter;

/**
 * Created by suzunshou on 2017/3/31.
 */
public class StringUtils {
    public static String upperFirstCharacter(String str) {
        char[] cs = str.toLowerCase().toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static String returnFilePath(String str) {
        return str.replace(".", "/") + "/";
    }

    public static StringWriter getWriter() {
        return new StringWriter();
    }


}
