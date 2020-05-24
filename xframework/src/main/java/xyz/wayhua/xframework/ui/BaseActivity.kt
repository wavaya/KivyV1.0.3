package xyz.wayhua.xframework.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import xyz.wayhua.xframework.R
import xyz.wayhua.xframework.tools.bus.XLiveDataBus
import xyz.wayhua.xframework.tools.immersionbar.XImmersionBar
import xyz.wayhua.xframework.utils.NightModelUtils

open  class BaseActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!NightModelUtils.getNightModeSwitch(this)) {
            setTheme(R.style.Theme_XFramework)
        } else {
            setTheme(R.style.Theme_XFramework_Night)
        }
        registerBusObserver()
        beforeInitViews(savedInstanceState)
        //初始化控件
        initViews(savedInstanceState)

        afterInitViews(savedInstanceState)
        //初始化沉浸式
        initImmersionBar()

    }

    protected open fun initImmersionBar() {
        XImmersionBar.immersion(this, true)

    }

    protected open fun afterInitViews(savedInstanceState: Bundle?) {

    }

    protected open fun initViews(savedInstanceState: Bundle?) {

    }

    protected open fun beforeInitViews(savedInstanceState: Bundle?) {

    }


    open fun registerBusObserver() {
        XLiveDataBus.registerChangeTheme(this)
        XLiveDataBus.registerCloseAll(this)

    }
}