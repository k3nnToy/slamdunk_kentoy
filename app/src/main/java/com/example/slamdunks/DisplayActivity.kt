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

        selectedEntry?.let { entry ->
            // Populate UI fields with the retrieved data
            binding.fullnameValue.text = entry.fullname
            binding.ageValue.text = entry.age.toString()
            binding.genderValue.text = entry.gender
            binding.addressValue.text = entry.address
            binding.favoriteColorValue.text = entry.favoriteColor ?: "Not provided"
            binding.favoriteFoodValue.text = entry.favoriteFood ?: "Not provided"
            binding.favoriteSportsValue.text = entry.favoriteSport ?: "Not provided"
            binding.favoriteOnlineGamesValue.text = entry.favoriteOnlineGames ?: "Not provided"
            binding.favoriteHobbyValue.text = entry.favoriteHobby ?: "Not provided"
            binding.favoriteBooksValue.text = entry.booksOrMovies ?: "Not provided"
            binding.favoriteSportOrGameValue.text = entry.sportOrGame ?: "Not provided"
            binding.favoriteDrawingOrPaintingValue.text = entry.drawingOrPainting ?: "Not provided"
            binding.favoriteFunHobbyValue.text = entry.funHobby ?: "Not provided"

            // Load the profile image using Glide
            Glide.with(this).load(entry.avatarId).into(binding.profileImg)
        } ?: run {
            // Handle case where the entry is null
            Toast.makeText(this, "Slambook entry not found", Toast.LENGTH_SHORT).show()
        }
    }
}
