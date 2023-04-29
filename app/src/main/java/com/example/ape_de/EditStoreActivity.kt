package com.example.ape_de

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ape_de.databinding.ActivityEditStoreBinding

class EditStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener{
            startActivity(Intent(this,UpdateStoreActivity::class.java))
        }
    }
}