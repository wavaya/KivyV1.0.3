package xyz.wayhua.kivyv103.ui.main


import org.koin.android.ext.android.inject
import xyz.wayhua.kivyv103.ui.main.fragment.MainFragment

import xyz.wayhua.xframework.ui.BaseActivityWithFragment

class MainActivity : BaseActivityWithFragment<MainFragment>() {

    override val fragment: MainFragment by inject<MainFragment>()


}
