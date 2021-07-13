package com.example.quakeinfo.screens.quakeoverview

//import com.example.quakeinfo.network.QuakeApi
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quakeinfo.network.QuakeApi
import com.example.quakeinfo.network.models.QuakeFeatures
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val INITIAL_MSG: String = "Nothing to show !!"
private const val NO_QUAKES_MSG: String = "No earthquakes found in this range"
private const val ERROR_MSG: String = "Failed to fetch info, please check your network once !"

class QuakeOverviewViewModel : ViewModel() {

    private var statusCode: Int

    var minQuakeMagnitude: Double
    var maxQuakeMagnitude: Double

    private val _quakeResponseFeatures = MutableLiveData<List<QuakeFeatures>>()
    val quakeResponseFeatures: LiveData<List<QuakeFeatures>>
        get() = _quakeResponseFeatures

    private val _quakeResponseCount = MutableLiveData<String>()
    val quakeResponseCount: LiveData<String>
        get() = _quakeResponseCount

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int>
        get() = _progressBarVisibility

    private val _rvVisibility = MutableLiveData<Int>()
    val rvVisibility: LiveData<Int>
        get() = _rvVisibility

    private val _statusInfoVisibility = MutableLiveData<Int>()
    val statusInfoVisibility: LiveData<Int>
        get() = _statusInfoVisibility

    private val _statusMessage = MutableLiveData<String>()
    val statusMessage: LiveData<String>
        get() = _statusMessage

    init {
        Log.i("QuakeOverviewViewModel", "OverviewViewModel created !")
        statusCode = 404
        minQuakeMagnitude = 1.0
        maxQuakeMagnitude = 10.0

        _quakeResponseFeatures.value = arrayListOf()
        _quakeResponseCount.value = "0"

        _statusInfoVisibility.value = 0
        _statusMessage.value = INITIAL_MSG

        _rvVisibility.value = 8
        _progressBarVisibility.value = 8
    }

    fun getQuakeInfo() {
        _statusInfoVisibility.value = 8
        _rvVisibility.value = 8
        _progressBarVisibility.value = 0

        // Launching coroutines to do network call in a background thread
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val quakeResponse = QuakeApi.retrofitService.getQuakeInfo(
                    minmagnitude = minQuakeMagnitude,
                    maxmagnitude = maxQuakeMagnitude
                )

                val quakeResponseFeaturesSize = if (quakeResponse.features.isEmpty()) 0 else quakeResponse.features.size

                statusCode = quakeResponse.metadata.status
                if (statusCode == 200) {
                    // If response is fetched successfully then switching back to Main(UI) thread
                    // and setting appropriate the values to display the results
                    withContext(Dispatchers.Main) {
                        if (quakeResponseFeaturesSize == 0) {
                            _statusMessage.value = NO_QUAKES_MSG
                            _statusInfoVisibility.value = 0
                        }
                        _quakeResponseFeatures.value = quakeResponse.features
                        _quakeResponseCount.value = quakeResponseFeaturesSize.toString()
                        _progressBarVisibility.value = 8
                        _rvVisibility.value = if (quakeResponseFeaturesSize == 0) 8 else 0
                        Log.d("NOW IN UI", "wow")
                    }
                }
            } catch (e: Exception) {
                // Switching to Main(UI) thread to display the error
                withContext(Dispatchers.Main) {
                    _progressBarVisibility.value = 8
                    _statusMessage.value = ERROR_MSG
                    _statusInfoVisibility.value = 0
                }
            }
        }
    }

}