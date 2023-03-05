package com.example.vk_gif.view.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vk_gif.R
import com.example.vk_gif.view.main.vm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
    }
}