package com.sschmitz.kmm_weather.android

import android.app.Application
import com.sschmitz.kmm.data.di.Repositories
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import timber.log.Timber

class App : Application(), DIAware {
  override val di by DI.lazy {
    import(androidXModule(this@App))

    import(Repositories.diContainer)
    import(ViewModels.diContainer)
  }

  override fun onCreate() {
    super.onCreate()

    Timber.plant(Timber.DebugTree())
  }
}
