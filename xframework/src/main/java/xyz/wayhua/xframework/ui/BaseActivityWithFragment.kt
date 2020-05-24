package xyz.wayhua.xframework.ui

import android.os.Bundle
import xyz.wayhua.xframework.R


abstract class BaseActivityWithFragment<out F : BaseFragment>() : BaseActivity() {
    override fun initViews(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_with_fragment)

        val bundle = Bundle()
        setBundles(bundle)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.rl, fragment!!).commit()
    }

    /***
     * 设置参数直接放在bundle
     * @param bundle
     */
    protected fun setBundles(bundle: Bundle?) {}
    override fun onResume() {
        super.onResume()
        fragment.onResume()
    }


    protected abstract val  fragment: F
    // = createFragment()

//    abstract fun createFragment(): F
//    protected fun createFragment(): F {
//        val parameterizedType = this.javaClass.genericSuperclass as ParameterizedType?
//
//        val types = parameterizedType!!.actualTypeArguments
//        var vmclss :Class<*>
//
//        if (types[0] is ParameterizedType) {
//            vmclss = (types[0] as ParameterizedType).rawType as Class<*>
//
//        } else {
//            vmclss = types[0] as Class<*>
//        }
//        val vmClazz = vmclss
//        try {
//            val constructor = vmClazz.getConstructor()
//            return constructor.newInstance() as F
//        } catch (e: Exception) {
//
//            throw RuntimeException(e.message)
//        }
//
//    }
}