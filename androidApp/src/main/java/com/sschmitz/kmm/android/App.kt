package com.sschmitz.kmm.android

import android.app.Application
import com.sschmitz.kmm.domain.di.Repositories
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule

class App : Application(), DIAware {
  override val di by DI.lazy {
    import(androidXModule(this@App))
    import(Repositories.diContainer)
  }
}
