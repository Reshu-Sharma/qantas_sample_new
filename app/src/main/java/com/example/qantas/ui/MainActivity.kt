package com.example.qantas.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.qantas.R
import com.example.qantas.databinding.ActivityBaseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding:ActivityBaseBinding ?= null
    private var navController: NavController?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        navController = supportFragmentManager.findFragmentById(R.id.navHostFragment)?.findNavController()
    }

}