package com.example.slamdunks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.slamdunks.databinding.ActivityDisplayBinding

class DisplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the SlambookEntry object from the Intent
        val selectedEntry = intent.getSerializableExtra("slambookEntry") as? SlambookEntry

        if (selectedEntry == null) {
            // Handle the case where the SlambookEntry is not found or is null
            Toast.makeText(this, "Slambook entry not found", Toast.LENGTH_SHORT).show()
        } else {
            // Populate UI fields with the retrieved data from Form1 and Form2
            binding.fullnameValue.text = selectedEntry.fullname
            binding.ageValue.text = selectedEntry.age.toString()
            binding.genderValue.text = selectedEntry.gender
            binding.addressValue.text = selectedEntry.address

            // Display Favorite Data
            binding.favoriteColorValue.text = selectedEntry.favoriteColor ?: "Not provided"
            binding.favoriteFoodValue.text = selectedEntry.favoriteFood ?: "Not provided"
            binding.favoriteSportsValue.text = selectedEntry.favoriteSport ?: "Not provided"
            binding.favoriteOnlineGamesValue.text = selectedEntry.favoriteOnlineGames ?: "Not provided"

            // Display Hobby Data
            binding.favoriteHobbyValue.text = selectedEntry.favoriteHobby ?: "Not provided"
            binding.favoriteBooksValue.text = selectedEntry.booksOrMovies ?: "Not provided"
            binding.favoriteSportOrGameValue.text = selectedEntry.sportOrGame ?: "Not provided"
            binding.favoriteDrawingOrPaintingValue.text = selectedEntry.drawingOrPainting ?: "Not provided"
            binding.favoriteFunHobbyValue.text = selectedEntry.funHobby ?: "Not provided"

            // Load the profile image using Glide
            Glide.with(this).load(selectedEntry.avatarId).into(binding.profileImg)
        }
    }
}
