package com.example.ape_de

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.ape_de.databinding.ActivityRegisterationPageBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class RegisterationPageActivity : AppCompatActivity() {


//    private lateinit var binding: ActivityRegisterationPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityRegisterationPageBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_registeration_page)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val fullName : EditText = findViewById(R.id.et_fullname)
        val email : EditText = findViewById(R.id.et_email)
        val userName : EditText = findViewById(R.id.et_username)
        val password : EditText = findViewById(R.id.et_password)
        val confirmPassword : EditText = findViewById(R.id.et_confirm_password)
        val registerBtn : AppCompatButton = findViewById(R.id.btn_register)
        val passwordLayout : TextInputLayout = findViewById(R.id.til_password)
        val confirmPasswordLayout : TextInputLayout = findViewById(R.id.til_confirm_password)

        registerBtn.setOnClickListener {
            val name = fullName.text.toString()
            val mail = email.text.toString()
            val username = userName.text.toString()
            val pwd = password.text.toString()
            val confirmPwd = confirmPassword.text.toString()

            passwordLayout.isPasswordVisibilityToggleEnabled = true
            confirmPasswordLayout.isPasswordVisibilityToggleEnabled = true

            if (name.isEmpty() || mail.isEmpty() || username.isEmpty() || pwd.isEmpty() || confirmPwd.isEmpty()){
                if (name.isEmpty()){
                    fullName.error = "Enter your name"
                }
                if (mail.isEmpty()){
                    email.error = "Enter your email address"
                }
                if (username.isEmpty()){
                    userName.error = "Enter your username"
                }
                if (pwd.isEmpty()){
                    passwordLayout.isPasswordVisibilityToggleEnabled = false
                    password.error = "Enter your password"
                }
                if (confirmPwd.isEmpty()){
                    confirmPasswordLayout.isPasswordVisibilityToggleEnabled = false
                    confirmPassword.error = "Re-enter your password"
                }
                Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show()

            }else if (mail.matches(emailPattern.toRegex())){
                email.error = "Enter valid email address"
                Toast.makeText(this, "Enter valid email address", Toast.LENGTH_SHORT).show()

            }else if(pwd.length < 6){
                password.error = "Password should be more than 6 characters"
                Toast.makeText(this, "Password should be more than 6 characters", Toast.LENGTH_SHORT).show()

            }else if (pwd != confirmPwd){
                confirmPassword.error = "Password not match, try again"
                Toast.makeText(this, "Password not match, try again", Toast.LENGTH_SHORT).show()

            }else{
                firebaseAuth.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener {
                    if(it.isSuccessful){
                        val databaseRef = database.reference.child("users").child(firebaseAuth.currentUser!!.uid)
                        val users : Users = Users(name, mail, username, firebaseAuth.currentUser!!.uid)

                        databaseRef.setValue(users).addOnCompleteListener {
                            if (it.isSuccessful){
                                val intent = Intent(this, LoginPageActivity::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{

                        Toast.makeText(this, "Something went wrong, Try again3", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}