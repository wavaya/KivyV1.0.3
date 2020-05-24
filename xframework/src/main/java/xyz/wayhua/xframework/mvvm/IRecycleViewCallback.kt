package xyz.wayhua.xframework.mvvm

import android.view.View

/**
 * Created by 黄卫华(wayhua@126.com) on 2017/7/24.
 */
interface IRecycleViewCallback<T> {
    fun onModelClicked(model: T, sharedView: View?)

    /***
     * 长按方法，默认方法为空
     * @param model
     * @param sharedView
     */
    fun onModelLongClicked(model: T, sharedView: View?): Boolean {
        return true
    }
}