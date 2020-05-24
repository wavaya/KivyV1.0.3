package xyz.wayhua.xframework.tools.immersionbar

import android.R
import android.app.Activity
import androidx.annotation.IdRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.gyf.immersionbar.ImmersionBar

/**
 * Created by 黄卫华(wayhua@126.com) on 2019/11/26 0026.
 * 主要是统一ImmersionBar的编写风格，使用
 * XImmersionBar后可以保证风格统一，同时
 * 修改起来方便，如果觉得不够灵活，特殊情况下也可以
 * 参照原来的编写。
 * 主要使用到三个参数，Fragment/activity ,toolbar(id,实体），是否显示深色字体颜色
 */
object XImmersionBar {
    /***
     * 简单的使用，传入fragment以及toolbar的Id
     * @param fragment
     * @param toolbar id
     */
    @JvmOverloads
    fun immersion(fragment: Fragment, @IdRes toolbar: Int, isDarkFont: Boolean = true) {
        getWith(fragment)
                .statusBarDarkFont(isDarkFont)
                .titleBar(toolbar)
                .init()
    }

    @JvmOverloads
    fun immersion(fragment: Fragment, toolbar: Toolbar?, isDarkFont: Boolean = true) {
        getWith(fragment)
                .statusBarDarkFont(isDarkFont)
                .titleBar(toolbar)
                .init()
    }

    @JvmOverloads
    fun immersion(activity: Activity, @IdRes toolbar: Int, isDarkFont: Boolean = true) {
        getWith(activity)
                .statusBarDarkFont(isDarkFont)
                .titleBar(toolbar)
                .init()
    }

    @JvmOverloads
    fun immersion(activity: Activity, toolbar: Toolbar?, isDarkFont: Boolean = true) {
        getWith(activity)
                .statusBarDarkFont(true)
                .titleBar(toolbar)
                .init()
    }

    @JvmOverloads
    fun immersion(fragment: Fragment, isDarkFont: Boolean = true) {
        getWith(fragment)
                .statusBarDarkFont(isDarkFont)
                .init()
    }

    @JvmStatic
    @JvmOverloads
    fun immersion(activity: Activity, isDarkFont: Boolean = true) {
        getWith(activity)
                .statusBarDarkFont(isDarkFont)
                .init()
    }

    /***
     * 进行了一些简单的初始化，
     * 如navigationBarColor为黑色
     *
     * @param fragment
     * @return
     */
    fun getWith(fragment: Fragment): ImmersionBar {
        return ImmersionBar
                .with(fragment)
                .navigationBarColor(R.color.black)
    }

    /***
     * 进行了一些简单的初始化，
     * 如navigationBarColor为黑色
     * @param activity
     * @return
     */
    fun getWith(activity: Activity): ImmersionBar {
        return ImmersionBar.with(activity)
                .navigationBarColor(R.color.black)
                .statusBarDarkFont(true)
    }
}