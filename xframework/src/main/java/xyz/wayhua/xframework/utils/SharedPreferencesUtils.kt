package xyz.wayhua.xframework.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by 黄卫华(wayhua@126.com) on 2019/10/9 0009.
 */
object SharedPreferencesUtils {
    const val PREFS = "share_data"
    fun <T> putT(context: Context, key: String, obj: T) {
        val sp: SharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val editor = sp.edit()
        when {
            obj is String -> editor.putString(key, obj)
            obj is Int -> editor.putInt(key, obj)
            obj is Boolean -> editor.putBoolean(key, obj)
            obj is Float -> editor.putFloat(key, obj)
            obj is Long -> editor.putLong(key, obj)
            else -> editor.putString(key, obj.toString())
        }
        editor.apply()
    }

    fun <T> getT(context: Context, key: String, default: T): T {
        val sp: SharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

        return when {
            default is String -> sp.getString(key, default) as T
            default is Int -> sp.getInt(key, default) as T
            default is Boolean -> sp.getBoolean(key, default) as T
            default is Float -> sp.getFloat(key, default) as T
            default is Long -> sp.getLong(key, default) as T

            else -> default
        }
    }
}