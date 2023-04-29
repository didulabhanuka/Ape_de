package com.example.ape_de

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ape_de.databinding.ActivityAfterCreateStoreBinding

class AfterCreateStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAfterCreateStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAfterCreateStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.emptyEditstore.setOnClickListener{
            startActivity(Intent(this,EditStoreActivity::class.java))
        }
    }
}