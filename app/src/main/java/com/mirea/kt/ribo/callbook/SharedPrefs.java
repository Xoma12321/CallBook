package com.mirea.kt.ribo.callbook;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static final String PREF_NAME = "MyPreferences";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private SharedPrefs() {
    }

    private static void initSharedPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    // Метод для сохранения значения в SharedPreferences
    public static void saveBool(Context context, String key, Boolean value) {
        initSharedPreferences(context);
        editor.putBoolean(key, value);
        editor.apply();
    }

    // Метод для получения значения из SharedPreferences
    public static Boolean getBool(Context context, String key) {
        initSharedPreferences(context);
        return sharedPreferences.getBoolean(key, false);
    }

    public static void clearSharedPreferences(Context context) {
        initSharedPreferences(context);
        editor.clear();
        editor.apply();
    }
}