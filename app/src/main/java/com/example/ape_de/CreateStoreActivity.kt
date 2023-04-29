package com.example.ape_de

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.ape_de.databinding.ActivityCreateStoreBinding

class CreateStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf("Bathic Store","Hand Craft Store")
        val adapter = ArrayAdapter(this,R.layout.list_item,items)
        binding.dropdownMenu.setAdapter(adapter)

        binding.btnCreateNewStore.setOnClickListener{
            startActivity(Intent(this, AfterCreateStoreActivity::class.java))
        }
    }
}