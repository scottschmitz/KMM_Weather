package com.sschmitz.kmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sschmitz.kmm.domain.Greeting
import android.widget.TextView
import com.sschmitz.kmm.domain.contract.WeatherContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.kodein.di.DIAware
import org.kodein.di.instance
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity(), CoroutineScope, DIAware{

    override val di by lazy { (applicationContext as App).di }

    val repository: WeatherContract by instance()

    private var job = Job()
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()


        launch {
            val fullForecast = repository.getFullForecast()
            tv.text = fullForecast.forecasts.first().detailedForecast
        }
    }
}
