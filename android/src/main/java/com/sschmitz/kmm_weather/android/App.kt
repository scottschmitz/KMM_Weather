package com.sschmitz.kmm_weather.android

import android.app.Application
import com.sschmitz.kmm.data.di.Repositories
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule

class App : Application(), DIAware {
  override val di by DI.lazy {
    import(androidXModule(this@App))
    import(Repositories.diContainer)
  }
}
