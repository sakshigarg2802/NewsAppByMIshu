package com.example.newsappbymishu.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.newsappbymishu.R
import com.example.newsappbymishu.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.activity.viewModels

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        getTopNewsHeadline()
    }

    @SuppressLint("RepeatOnLifecycleWrongUsage")
    private fun getTopNewsHeadline() {
        mainViewModel.getTopNewsHeadline()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.uiState.collect {
                    when (it) {
                        is MainViewModel.NewsUiState.Success -> {
                                Log.d("test-> ", it.articleList[0].title.toString())
                        }

                        is MainViewModel.NewsUiState.Error -> {

                        }

                        is MainViewModel.NewsUiState.Loading -> {

                        }
                    }
                }
            }
        }
    }

}
