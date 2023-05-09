package com.example.ape_de

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.ape_de.databinding.ActivityAfterCreateStoreBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AfterCreateStoreActivity : AppCompatActivity() {

    private lateinit var dbReference: DatabaseReference
    private lateinit var storeId: String

    private lateinit var empStoreName : TextView
    private lateinit var empStoreDesc : TextView
    private lateinit var empStoreAddress : TextView
    private lateinit var empStoreNumber : TextView
    private lateinit var empEditBtn : Button
    private lateinit var empAddBtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_create_store)

        empStoreName = findViewById(R.id.emp_storeName)
        empStoreDesc = findViewById(R.id.emp_storeAbout)
        empStoreAddress = findViewById(R.id.emp_storeAddress)
        empStoreNumber = findViewById(R.id.emp_storeNumber)
        empEditBtn = findViewById(R.id.emp_editStoreBtn)
        empAddBtn = findViewById(R.id.emp_addItemsBtn)

        dbReference = FirebaseDatabase.getInstance().getReference("Stores")

        // Retrieve the StoreId from the intent
        storeId = intent.getStringExtra("storeName") ?: ""

        //Retrieve the details from the database and display it
        dbReference.child(storeId).get().addOnSuccessListener { snapshot ->
            val storeModel = snapshot.getValue(StoreModel::class.java)
            storeModel?.let {
                empStoreName.setText(it.storeName)
                empStoreDesc.setText(it.storeDesc)
                empStoreAddress.setText(it.storeAddress)
                empStoreNumber.setText(it.storePhone)
            }
        }.addOnFailureListener{err ->
            Toast.makeText(this, "Failed to retrieve data. Error: ${err.message}", Toast.LENGTH_SHORT).show()
        }

        empEditBtn.setOnClickListener {
            val intent = Intent(this, UpdateStoreActivity::class.java)
            intent.putExtra("storeName",  storeId)
            startActivity(intent)
        }

    }
}