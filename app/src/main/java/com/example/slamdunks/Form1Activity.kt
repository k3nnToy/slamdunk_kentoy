package com.example.slamdunks

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.slamdunks.databinding.ActivityForm1Binding

class Form1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityForm1Binding

    companion object {
        val userList = mutableListOf<SlambookEntry>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityForm1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle NEXT button click
        binding.nextButton.setOnClickListener {
            handleNextButtonClick()
        }
    }

    private fun handleNextButtonClick() {
        val fullName = binding.nameValue.text.toString().trim()
        val age = binding.ageValue.text.toString().trim()
        val gender = binding.genderSpinner.selectedItem.toString().trim()
        val address = binding.addressValue.text.toString().trim()

        if (fullName.isNotEmpty() && age.isNotEmpty() && gender.isNotEmpty() && address.isNotEmpty()) {
            // Create new SlambookEntry and add to the list
            val newEntry = SlambookEntry(fullName, age, gender, address)
            userList.add(newEntry)  // Save the new entry to the shared list

            // Navigate to FavoriteActivity and pass the data
            val intent = Intent(this, FavoriteActivity::class.java)  // Change to FavoriteActivity
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }
}


