package com.example.slamdunks

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.slamdunks.databinding.ActivityForm1Binding

class Form1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityForm1Binding

    companion object {
        // Shared list to store all users
        val userList = mutableListOf<SlambookEntry>()
    }

    private var avatarId: Int = R.drawable.boy1  // Default avatar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityForm1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle NEXT button click
        binding.nextButton.setOnClickListener {
            handleNextButtonClick()
        }

        // Handle avatar image click to open AvatarSelectionActivity
        binding.profileImg.setOnClickListener {
            openAvatarSelection()
        }
    }

    // Function to handle the "Next" button click
    private fun handleNextButtonClick() {
        val fullName = binding.nameValue.text.toString().trim()
        val ageText = binding.ageValue.text.toString().trim()
        val gender = binding.genderSpinner.selectedItem.toString().trim()
        val address = binding.addressValue.text.toString().trim()

        // Validate all the input fields
        when {
            fullName.isEmpty() -> {
                showToast("Please enter your full name.")
            }
            ageText.isEmpty() -> {
                showToast("Please enter your age.")
            }
            gender == "Select Gender" -> {
                showToast("Please select a gender.")
            }
            address.isEmpty() -> {
                showToast("Please enter your address.")
            }
            avatarId == R.drawable.boy1 -> {
                showToast("Please select an avatar.")
            }
            else -> {
                // Convert age to integer and handle potential parsing errors
                val age = ageText.toIntOrNull()
                if (age == null) {
                    showToast("Please enter a valid age.")
                } else {
                    // Create a new SlambookEntry with avatarId and add to the user list
                    val newEntry = SlambookEntry(fullName, age, gender, address, avatarId)
                    userList.add(newEntry) // Save the new entry to the shared list

                    // Navigate to the next activity (e.g., FavoriteActivity)
                    navigateToFavoriteActivity(newEntry)
                }
            }
        }
    }

    // Utility function to show a Toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Navigate to the FavoriteActivity and pass the new entry
    private fun navigateToFavoriteActivity(newEntry: SlambookEntry) {
        val intent = Intent(this, FavoriteActivity::class.java).apply {
            putExtra("slambookEntry", newEntry) // Pass the SlambookEntry
        }
        startActivity(intent)
        finish() // Optional: to finish the current activity and prevent back navigation
    }

    // Open Avatar Selection Activity
    private fun openAvatarSelection() {
        val intent = Intent(this, AvatarSelectionActivity::class.java)
        startActivityForResult(intent, 100)
    }

    // Handle the result from AvatarSelectionActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val selectedAvatarId = data?.getIntExtra("selectedAvatar", -1)
            if (selectedAvatarId != null && selectedAvatarId != -1) {
                avatarId = selectedAvatarId
                binding.profileImg.setImageResource(avatarId)  // Set the selected avatar in ImageView
            }
        }
    }
}
