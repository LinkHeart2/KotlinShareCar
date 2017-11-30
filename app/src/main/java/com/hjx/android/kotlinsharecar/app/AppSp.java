package com.hjx.android.kotlinsharecar.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hjx on 0024 5-24.
 * You can make it better
 */

public class AppSp {
    private static final String USER_INFO = "userinfo";
    private static final String ORDER_INFO = "orderInfo";

    /**
     * USER_INFO
     */
    public static final String ACCESS_TOKEN = "accessToken";

    public static final String ID = "id";
    public static final String NICK_NAME = "nickname";
    public static final String HEAD_IMG = "headImg";
    public static final String MOBILE = "mobile";
    public static final String SEX = "sex";
    public static final String BIRTH_DAY = "birthday";
    public static final String EMAIL = "email";
    //会员缴纳押金情况(1已缴,0未缴)
    public static final String DEPOSIT = "deposit";
    //驾照审核情况(1已通过,0未通过)
    public static final String LICENSE = "license";
    //当前进行中的订单ID
    public static final String ORDER_ID = "orderId";

    public static final String TEMP_IMG_HEADER = "tempImageHeader";
    public static final String TEMP_IMG_TIME = "tempImageTime";


    public static final String IS_OPEN_FINGER = "isOpenFinger";

    public static final String VERSION = "version";
    /**
     * USER_INFO END
     */


    /**
     * ORDER_INFO
     */
    //订单状态
    public static final String ORDER_STATUS = "orderStatus";
    //订单开始时间（暂用）
    public static final String ORDER_START_TIME = "orderStartTime";
    //车牌号
    public static final String ORDER_CAR_NO = "orderCarNo";
    //座位数
    public static final String ORDER_SET_NUM = "orderSetNum";
    //续航
    public static final String ORDER_NOW_LIFE = "orderNowLife";
    //车型
    public static final String ORDER_CAR_TYPE = "orderCarType";
//    //里程
//    public static final String ORDER_DISTANCE = "orderDistance";
//    //时间
//    public static final String ORDER_TIME = "orderTime";
//    //价格
//    public static final String ORDER_PRICE = "orderPrice";
    /**
     * ORDER_INFO END
     */


    private static SharedPreferences mUserSp;
    private static SharedPreferences mOrderSp;

    public static void init(Context context) {
        mUserSp = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        mOrderSp = context.getSharedPreferences(ORDER_INFO, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor editUser() {
        return mUserSp.edit();
    }

    public static SharedPreferences getUserSp() {
        return mUserSp;
    }

    public static void clearUser() {
        mUserSp.edit().clear().commit();
    }

    public static SharedPreferences.Editor editOrder() {
        return mOrderSp.edit();
    }

    public static SharedPreferences getOrderSp() {
        return mOrderSp;
    }

    public static void clearOrder() {
        mOrderSp.edit().clear().commit();
    }

    public static String getAccessToken() {

        return mUserSp.getString(ACCESS_TOKEN, "");
    }

    public static long getMemberID() {
        return mUserSp.getLong(ID, -1L);
    }
}
