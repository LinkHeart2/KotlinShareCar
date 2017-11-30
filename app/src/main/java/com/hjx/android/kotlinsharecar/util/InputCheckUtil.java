package com.hjx.android.kotlinsharecar.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by hjx on 0010 4-10.
 * You can make it better
 */

public class InputCheckUtil {

    public static final int CHINESS_ENGLISH = 0;
    public static final int CHINESS = 1;
    public static final int ENGLISH_NUM = 2;
    public static final int WEIXIN = 3;

    public static void addTextLimit(final EditText et, final int limitType) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editable = et.getText().toString();
                String str;
                switch (limitType) {
                    case CHINESS_ENGLISH:
                        str = stringECFilter(editable.toString());
                        break;
                    case CHINESS:
                        str = stringCFilter(editable.toString());
                        break;
                    case ENGLISH_NUM:
                        str = stringENFilter(editable.toString());
                        break;
                    case WEIXIN:
                        str = stringWeixinFilter(editable.toString());
                        break;
                    default:
                        str = stringALLFilter(editable.toString());
                }
                if (!editable.equals(str)) {
                    et.setText(str);
                    //设置新的光标所在位置
                    et.setSelection(str.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //中英
    public static String stringECFilter(String str) throws PatternSyntaxException {
        // 仅仅同意字母和汉字
        String regEx = "[^a-zA-Z\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    //中
    public static String stringCFilter(String str) throws PatternSyntaxException {
        // 仅仅同意汉字
        String regEx = "[^\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    //英数
    public static String stringENFilter(String str) throws PatternSyntaxException {
        // 仅仅同意字母和数字
        String regEx = "[^a-zA-Z0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    //中英数
    public static String stringALLFilter(String str) throws PatternSyntaxException {
        // 仅仅同意字母、数字和汉字
        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    //微信》仅支持6-20个字母、数字、下划线或减号，以字母开头。
    public static String stringWeixinFilter(String str) throws PatternSyntaxException {
        // 仅仅同意字母、数字和汉字
        String regEx = "[^a-zA-Z0-9_-]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


    //邮箱
    public static boolean emailCheck(String email) {
        String regEx = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    //微信号
    public static boolean weixinCheck(String weixin) {
        String regEx = "^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}+$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(weixin);
        return m.matches();
    }


}
