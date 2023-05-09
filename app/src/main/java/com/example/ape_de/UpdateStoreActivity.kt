package com.example.ape_de

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ape_de.databinding.ActivityUpdateStoreBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateStoreBinding
    private lateinit var storeId: String
    private lateinit var dbRef: DatabaseReference

    private lateinit var updateStoreName : EditText
    private lateinit var updateStoreAddress : EditText
    private lateinit var updateStoreNumber : EditText
    private lateinit var updateStoreDesc : EditText
    private lateinit var updateStoreCat : EditText
    private lateinit var updateBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf("Bathic Store","Hand Craft Store")
        val adapter = ArrayAdapter(this,R.layout.list_item,items)
        binding.upCategory.setAdapter(adapter)

        updateStoreName = findViewById(R.id.updateShopName)
        updateStoreAddress = findViewById(R.id.updateAddress)
        updateStoreNumber = findViewById(R.id.updatePhone)
        updateStoreDesc = findViewById(R.id.updateDescription)
        updateStoreCat = findViewById(R.id.upCategory)
        updateBtn = findViewById(R.id.updateStoreBtn)

        dbRef = FirebaseDatabase.getInstance().getReference("Stores")

        // Retrieve the StoreId from the intent
        storeId = intent.getStringExtra("storeName") ?: ""

        //Retrieve the details from the database and display it
        dbRef.child(storeId).get().addOnSuccessListener { snapshot ->
            val storeModel = snapshot.getValue(StoreModel::class.java)
            storeModel?.let {
                updateStoreName.setText(it.storeName)
                updateStoreAddress.setText(it.storeAddress)
                updateStoreNumber.setText(it.storePhone)
                updateStoreDesc.setText(it.storeDesc)
                updateStoreCat.setText(it.storeCat)
            }
        }.addOnFailureListener{err ->
            Toast.makeText(this, "Failed to retrieve data. Error: ${err.message}", Toast.LENGTH_SHORT).show()
        }

        updateBtn.setOnClickListener {
            updateProfile()
        }

    }

    private fun updateProfile() {
        val newStoreName = updateStoreName.text.toString()
        val newStoreAddress = updateStoreAddress.text.toString()
        val newStoreNumber = updateStoreNumber.text.toString()
        val newStoreDesc = updateStoreDesc.text.toString()
        val newStoreCat = updateStoreCat.text.toString()

        if(newStoreName.isEmpty()){
            updateStoreName.error = "Please fill this field"
        }
        if(newStoreAddress.isEmpty()){
            updateStoreAddress.error = "Please fill this field"
        }
        if(newStoreNumber.isEmpty()){
            updateStoreNumber.error = "Please fill this field"
        }
        if(newStoreDesc.isEmpty()){
            updateStoreDesc.error = "Please fill this field"
        }
        if(newStoreCat.isEmpty()){
            updateStoreCat.error = "Please fill this field"
        }

        val updateStore = StoreModel(newStoreName, newStoreAddress, newStoreNumber, newStoreDesc, newStoreCat)
        dbRef.child(storeId).setValue(updateStore).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Store updated successfully!", Toast.LENGTH_SHORT).show()
                finish()
            }else {
                Toast.makeText(this, "Failed to store profile. Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}