package com.example.dayzservers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dayzservers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.apply {
            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.search_item -> {
                      //  Toast.makeText(this@MainActivity, "search tab", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.favorites_item -> {
                       // Toast.makeText(this@MainActivity, "favorites tab", Toast.LENGTH_SHORT) .show()
                        true
                    }

                    else -> false
                }
            }
        }

    }
}