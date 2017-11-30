package com.hjx.android.kotlinsharecar.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hjx.android.kotlinsharecar.app.CarApplication;


/**
 * Created by hjx on 0016 5-16.
 * You can make it better
 */

public class UiUtil {

    /**
     * 获取指定dp
     *
     * @param context
     * @param value   dp值
     * @return
     */
    public static float getDp(Context context, int value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }


    public static String[] getStringArray(Context context, int stringArray) {
        return context.getResources().getStringArray(stringArray);
    }


    public static void accessActivity(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);

    }

    /**
     * 找到界面中的button和imagebutton设置点击事件
     *
     * @param view
     * @param listener
     */
    public static void findButtonAndSetOnClickListener(View view, View.OnClickListener listener) {
        if (view instanceof ViewGroup) {
            ViewGroup rootView = (ViewGroup) view;
            int childCount = rootView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = rootView.getChildAt(i);
                if (childView instanceof Button || childView instanceof ImageButton) {
                    childView.setOnClickListener(listener);
                } else {
                    findButtonAndSetOnClickListener(childView, listener);
                }

            }
        }
    }


    public static String getText(Object t) {
        if (t instanceof EditText) {
            return ((EditText) t).getText().toString().trim();
        } else if (t instanceof TextView) {
            return ((TextView) t).getText().toString().trim();
        }

        return null;
    }


    public static int getColor(int color) {
        return CarApplication.Companion.getContext().getResources().getColor(color);
    }

    public static String int2String(int count) {
        return String.valueOf(count);
    }

    public static Drawable getDrawable(int drawable) {
        return CarApplication.Companion.getContext().getResources().getDrawable(drawable);
    }

    public static void removeEtViewFocus(Context context,EditText et) {
        InputMethodManager mInputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        et.setFocusable(false);//设置输入框不可聚焦，即失去焦点和光标
        if (mInputMethodManager.isActive()) {
            mInputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), 0);// 隐藏输入法
        }
    }
}
