package br.dev.brunnofdc.utilator

import android.app.Application
import br.dev.brunnofdc.utilator.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class UtilatorApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UtilatorApplication)

            modules(appModule)
        }
    }
}