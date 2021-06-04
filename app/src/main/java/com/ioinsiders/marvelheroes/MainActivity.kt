package com.ioinsiders.marvelheroes


import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import com.ioinsiders.marvelheroes.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val wic = WindowInsetsControllerCompat(window, window.decorView)
            wic.isAppearanceLightStatusBars = true
            window.statusBarColor = Color.parseColor("#FAFAFA")  //Color.WHITE
        }

        binding.apply {
            tabLayout.addTab(tabLayout.newTab().setText("Popular"))
            tabLayout.addTab(tabLayout.newTab().setText("A-Z"))
            tabLayout.addTab(tabLayout.newTab().setText("Last Viewed"))
        }

    }
}