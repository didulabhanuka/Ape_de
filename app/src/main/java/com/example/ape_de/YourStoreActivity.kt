package com.example.ape_de

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ape_de.databinding.ActivityYourStoreBinding

class YourStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityYourStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.filledEditstore.setOnClickListener{
            startActivity(Intent(this,EditStoreActivity::class.java))
        }

    }
}