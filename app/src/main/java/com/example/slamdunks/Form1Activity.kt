package com.example.slamdunks

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.slamdunks.databinding.ActivityForm1Binding

class Form1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityForm1Binding

    companion object {
        // Shared list to store all users
        val userList = mutableListOf<SlambookEntry>()
    }

    private var avatarId: Int = R.drawable.profile  // Default avatar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityForm1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set an input filter to allow only two-digit numbers for age (10-99)
        binding.ageValue.filters = arrayOf(InputFilter.LengthFilter(2)) // Limit length to 2 digits

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

        var isValid = true  // Assume all fields are valid initially

        // Validate the name field
        when {
            fullName.isEmpty() -> {
                binding.nameValue.error = "This field cannot be empty"
                isValid = false
            }
            // Check if the name already exists in the user list
            userList.any { it.fullname.equals(fullName, ignoreCase = true) } -> {
                showNameExistsDialog() // Show a dialog if name exists
                isValid = false
            }
            else -> binding.nameValue.error = null
        }

        // Validate the age field
        when {
            ageText.isEmpty() -> {
                binding.ageValue.error = "This field cannot be empty"
                isValid = false
            }
            else -> binding.ageValue.error = null
        }

        // Validate the gender field (Spinner)
        when {
            gender == "Select Gender" -> {
                showToast("Please select a gender.")
                isValid = false
            }
        }

        // Validate the address field
        when {
            address.isEmpty() -> {
                binding.addressValue.error = "This field cannot be empty"
                isValid = false
            }
            else -> binding.addressValue.error = null
        }

        // Validate the avatar selection
        when {
            avatarId == R.drawable.profile -> {
                showToast("Please select an avatar.")
                isValid = false
            }
        }

        // If all fields are valid, proceed
        if (isValid) {
            // Convert age to integer and handle potential parsing errors
            val age = ageText.toIntOrNull()
            if (age == null || age < 10 || age > 99) {
                showToast("Please enter a valid age between 10 and 99.")
            } else {
                // Create a new SlambookEntry with avatarId and add to the user list
                val newEntry = SlambookEntry(fullName, age, gender, address, avatarId)
                userList.add(newEntry) // Save the new entry to the shared list

                // Navigate to the next activity (Form2Activity)
                navigateToFavActivity(newEntry)
            }
        }
    }

    // Utility function to show a Toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Show dialog if the name already exists
    private fun showNameExistsDialog() {
        AlertDialog.Builder(this)
            .setTitle("Name Exists")
            .setMessage("The name you entered already exists. Please choose a different name.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss() // Close the dialog
            }
            .setCancelable(false)
            .show()
    }

    // Navigate to Form2Activity and pass the new entry
    private fun navigateToFavActivity(newEntry: SlambookEntry) {
        val intent = Intent(this, FavActivity::class.java)
        intent.putExtra("slambookEntry", newEntry)
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
