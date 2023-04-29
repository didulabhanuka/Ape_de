package com.example.ape_de

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ape_de.databinding.ActivityRegisterationPageBinding

class RegisterationPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterationPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterationPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener{
            startActivity(Intent(this,LoginPageActivity::class.java))
        }

        binding.tvHaveAccount.setOnClickListener{
            startActivity(Intent(this,LoginPageActivity::class.java))
        }
    }
}