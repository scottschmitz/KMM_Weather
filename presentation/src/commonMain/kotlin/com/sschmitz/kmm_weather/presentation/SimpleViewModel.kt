package com.sschmitz.kmm_weather.presentation

import com.sschmitz.kmm_weather.presentation.forecast.ForecastState
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class SimpleViewModel : ViewModel() {
  private val _mutableForecastStateLiveData = MutableLiveData<ForecastState>(ForecastState.Initial)
  val forecastStateLiveData: LiveData<ForecastState> = _mutableForecastStateLiveData

    private val _counter: MutableLiveData<Int> = MutableLiveData(0)
    val counter: LiveData<String> = _counter.map { it.toString() }

    fun onCounterButtonPressed() {
        val current = _counter.value
        _counter.value = current + 1
    }
}
