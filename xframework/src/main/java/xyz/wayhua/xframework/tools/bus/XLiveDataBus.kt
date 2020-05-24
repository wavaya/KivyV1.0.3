package xyz.wayhua.xframework.tools.bus


import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus

import xyz.wayhua.xframework.ui.BaseActivity

object XLiveDataBus {
    const val KEY_CLOSE_ALL_PAGE = "key_close_all_page"
    const val KEY_CHANG_THEME = "key_change_theme"
    fun sendCloseAll() {
        LiveEventBus.get(KEY_CLOSE_ALL_PAGE).post(true)
    }

    @JvmStatic
    fun sendChangeTheme() {
        LiveEventBus.get(KEY_CHANG_THEME).post(true)
    }

    @JvmStatic
    fun registerChangeTheme(activity: BaseActivity) {
        LiveEventBus
            .get(KEY_CHANG_THEME, Boolean::class.java)
            .observe(activity, Observer  { b ->
                if (b!!) {
                    activity.recreate()
                }
            })


    }

    @JvmStatic
    fun registerCloseAll(activity: BaseActivity) {
        LiveEventBus
            .get(KEY_CLOSE_ALL_PAGE, Boolean::class.java)
            .observe(activity, Observer { b ->
                if (b!!) {
                    activity.finish()
                }
            })
    }
}