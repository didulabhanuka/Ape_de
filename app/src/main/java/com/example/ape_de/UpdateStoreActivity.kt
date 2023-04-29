package com.example.ape_de

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.ape_de.databinding.ActivityUpdateStoreBinding

class UpdateStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf("Bathic Store","Hand Craft Store")
        val adapter = ArrayAdapter(this,R.layout.list_item,items)
        binding.dropdownMenu.setAdapter(adapter)

        binding.updateStore.setOnClickListener{
            startActivity(Intent(this,YourStoreActivity::class.java))
        }

    }
}