package com.daro.feature.flags.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.daro.feature.flags.R
import com.daro.feature.flags.data.enties.Source
import com.daro.feature.flags.databinding.ActivityMainBinding
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
                putBoolean("feature_name1", false)
                putBoolean("feature_name2", false)
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
                else -> Source.SharedPrefs
            }
            viewModel.updateStatus(featureSource)
        }
    }

}