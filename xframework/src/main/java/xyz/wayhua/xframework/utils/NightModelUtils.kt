package xyz.wayhua.xframework.utils

import android.content.Context

/**
 * Created by 黄卫华(wayhua@126.com) on 2019/10/9 0009.
 */
object NightModelUtils {
    const val KEY_NIGHT_MODE_SWITCH: String = "night_mode_switch"

    fun getNightModeSwitch(context: Context): Boolean {
        return SharedPreferencesUtils.getT(context.applicationContext, KEY_NIGHT_MODE_SWITCH, false)
    }

    fun setNightModeSwitch(context: Context, onoff: Boolean) {
        SharedPreferencesUtils.putT(context.applicationContext, KEY_NIGHT_MODE_SWITCH, onoff)
    }

    fun switchTheme(context: Context) {
        var lastTheme: Boolean = getNightModeSwitch(context)
        setNightModeSwitch(context, !lastTheme)
        //发送更换主题
        //暂时未开发
    }
}