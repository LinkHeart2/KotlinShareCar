package com.hjx.android.kotlinsharecar.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hjx on 0024 5-24.
 * You can make it better
 */

public class CheckUtil {
    public static boolean isPhone(String str) {
        Pattern p = Pattern.compile("^1[3-8]\\d{9}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isEmail(String str) {
        Pattern p = Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
