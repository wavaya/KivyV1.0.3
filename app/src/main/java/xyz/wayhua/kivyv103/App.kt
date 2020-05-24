package xyz.wayhua.kivyv103

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import xyz.wayhua.kivyv103.di.allModules
import xyz.wayhua.xframework.BaseApplication

class App :BaseApplication(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@App)
            modules(allModules)
        }
    }
}