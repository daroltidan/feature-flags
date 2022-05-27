package com.daro.feature.flags.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.daro.feature.flags.R
import com.daro.feature.flags.data.enties.Source
import com.daro.feature.flags.databinding.ActivityMainBinding
import com.daro.feature.flags.ui.App.Constants.FEATURE_1
import com.daro.feature.flags.ui.App.Constants.FEATURE_2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupDefaultSharedValues()
        setupFeatures()
        setupObservables()
    }

    private fun setupDefaultSharedValues() {
        sharedPreferences.edit()
            .apply {
                putBoolean(FEATURE_1, false)
                putBoolean(FEATURE_2, false)
            }
            .apply()
    }

    private fun setupObservables() {
        lifecycleScope.launchWhenCreated {
            viewModel.result.collectLatest {
                binding.featuresStatus.text = it
            }
        }
    }

    private fun setupFeatures() {
        binding.switch1.setOnCheckedChangeListener { _, i ->
            val featureSource = when (i) {
                R.id.firebase -> Source.Firebase
                R.id.json -> Source.ConfigJson
                R.id.prefs -> Source.SharedPrefs
                R.id.retrofit -> Source.Retrofit
                else -> throw IllegalStateException("not implemented")
            }
            viewModel.updateStatus(featureSource)
        }
    }

}