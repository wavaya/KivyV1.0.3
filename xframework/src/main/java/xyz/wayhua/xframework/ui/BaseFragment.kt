package xyz.wayhua.xframework.ui

import android.os.Bundle

import android.view.View

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.gyf.immersionbar.ImmersionBar

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        beforeInitViews(view, savedInstanceState)
        initViews(view, savedInstanceState)
        afterInitViews(view, savedInstanceState)
        fitsLayoutOverlap()
    }



    protected open var toolbar: Toolbar? = null

    protected open var statusBarView: View? = null


    protected open fun fitsLayoutOverlap() {
        if (statusBarView != null) {
            ImmersionBar.setStatusBarView(this, statusBarView)
        } else {
            if (toolbar != null) {
                ImmersionBar.setTitleBar(this, toolbar)
            }
        }

    }

    protected open fun afterInitViews(view: View, savedInstanceState: Bundle?) {

    }

    protected open fun initViews(view: View, savedInstanceState: Bundle?) {


    }

    protected open fun beforeInitViews(view: View, savedInstanceState: Bundle?) {

    }


}