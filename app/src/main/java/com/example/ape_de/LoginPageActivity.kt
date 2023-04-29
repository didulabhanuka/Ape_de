package com.example.ape_de

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ape_de.databinding.ActivityLoginPageBinding

class LoginPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            startActivity(Intent(this,SellerAccountActivity::class.java))
        }

        binding.tvHaventAccount.setOnClickListener{
            startActivity(Intent(this,RegisterationPageActivity::class.java))
        }
    }
}