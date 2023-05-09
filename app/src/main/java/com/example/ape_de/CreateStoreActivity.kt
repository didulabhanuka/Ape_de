package com.example.ape_de

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.ape_de.databinding.ActivityCreateStoreBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateStoreBinding
    private lateinit var etShopName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etDescription: EditText
    private lateinit var etCategory: TextView
    private lateinit var btnCreateNewStore: AppCompatButton

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etShopName = findViewById(R.id.etShopName)
        etPhone = findViewById(R.id.etPhone)
        etDescription = findViewById(R.id.etDescription)
        etCategory = findViewById(R.id.etCategory)
        btnCreateNewStore = findViewById(R.id.btnCreateNewStore)

        dbRef = FirebaseDatabase.getInstance().getReference("Stores")

        btnCreateNewStore.setOnClickListener {
            saveStoreData()
        }

        //dropdown list
        val items = listOf("Bathic Store","Hand Craft Store")
        val adapter = ArrayAdapter(this,R.layout.list_item,items)
        binding.etCategory.setAdapter(adapter)
    }

    private fun saveStoreData() {

        //getting values
        val storeName = etShopName.text.toString()
        val storePhone = etPhone.text.toString()
        val storeDesc = etDescription.text.toString()
        val storeCat = etCategory.text.toString()

        if(storeName.isEmpty()){
            etShopName.error = "Please enter shop name"
        }
        if(storePhone.isEmpty()){
            etPhone.error = "Please enter contact number"
        }
        if(storeDesc.isEmpty()){
            etDescription.error = "Please enter shop description"
        }
        if(storeCat.isEmpty()){
            etCategory.error = "Please select a category"
        }

        val storeId = dbRef.push().key!!

        val store = StoreModel(storeName, storePhone, storeDesc, storeCat)

        dbRef.child(storeId).setValue(store).addOnCompleteListener{
            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
        }

    }

}