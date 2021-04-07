package com.style.map.core.storage.sp;

import android.content.SharedPreferences;

import com.style.map.BaseApplication;

/**
 * 状态存储
 */
public class EchoPreference {

    private static final String STAFF = "echo";


    public static SharedPreferences.Editor getAppPreferenceEdit(String s) {
        return BaseApplication.application.getSharedPreferences(s, 0).edit();
    }

    public static SharedPreferences getAppPreference(String s) {
        return BaseApplication.application.getSharedPreferences(s, 0);
    }


    /**
     * 保存是否登陆
     *
     * @param isLongin true 为登录
     */
    public static void putLogin(Boolean isLongin) {
        getAppPreferenceEdit(STAFF)
                .putBoolean("isLogin", isLongin)
                .commit();
    }

    /**
     * 获取是否登陆
     *
     * @return true 表示登录
     */
    public static boolean getLogin() {
        return getAppPreference(STAFF).getBoolean("isLogin", false);
    }


    /**
     * 保存用户账号
     *
     * @param loginName 账号
     */
    public static void putLoginName(String loginName) {
        getAppPreferenceEdit(STAFF)
                .putString("loginName", loginName)
                .commit();
    }

    /**
     * 获取LoginName 用户账号
     *
     * @return 返回用户账号
     */
    public static String getLoginName() {
        return getAppPreference(STAFF).getString("loginName",  "");
    }


    public static void loginSuccess(String loginName) {
        putLogin(true);
        putLoginName(loginName);
    }

    public static void clearLogin() {
        putLogin(false);
        putLoginName(null);
    }


}
