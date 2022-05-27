package com.daro.feature.flags.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.daro.feature.flags.data.Feature
import com.daro.feature.flags.data.FeatureManager
import com.daro.feature.flags.data.Source
import com.daro.feature.flags.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupFeatures()
        setupObservables()
    }

    private fun setupObservables() {
        lifecycleScope.launchWhenCreated {
            viewModel.result.collectLatest {
                binding.featuresStatus.text = it
            }
        }
    }

    private fun setupFeatures() {
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateStatus(isChecked)
        }
    }

}