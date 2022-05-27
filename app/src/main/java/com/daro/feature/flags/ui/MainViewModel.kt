package com.daro.feature.flags.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daro.feature.flags.data.Feature
import com.daro.feature.flags.data.FeatureManager
import com.daro.feature.flags.data.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val featureManager: FeatureManager
) : ViewModel() {

    private val _result = MutableStateFlow("")
    val result = _result.asStateFlow()

    init {
        updateStatus(false)
    }

    fun updateStatus(checked: Boolean) {
        viewModelScope.launch {
            val featureSource = when (checked) {
                true -> Source.Remote
                else -> Source.Local
            }

            val feature1Status = Feature(key = "feature_name1", source = featureSource)
            val feature2Status = Feature(key = "feature_name2", source = featureSource)

            val r = StringBuilder()
            r.appendLine("feature 1 is ${featureManager.isFeatureEnabled(feature1Status)}")
            r.appendLine("feature 2 is ${featureManager.isFeatureEnabled(feature2Status)}")
            _result.value = r.toString()
        }
    }

}