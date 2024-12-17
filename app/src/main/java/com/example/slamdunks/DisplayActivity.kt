package com.example.slamdunks

import android.os.Bundle
import android.util.Log
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
            Toast.makeText(this, "Slambook entry not found", Toast.LENGTH_SHORT).show()
        } else {
            Log.d("DisplayActivity", "Received SlambookEntry: $selectedEntry")


            binding.fullnameValue.text = selectedEntry.fullname
            binding.ageValue.text = selectedEntry.age.toString()
            binding.genderValue.text = selectedEntry.gender
            binding.addressValue.text = selectedEntry.address
            binding.favoriteColorValue.text = selectedEntry.favoriteColor
            binding.favoriteFoodValue.text = selectedEntry.favoriteFood
            binding.favoriteSportsValue.text = selectedEntry.favoriteSport
            binding.favoriteOnlineGamesValue.text = selectedEntry.favoriteOnlineGames
            binding.favoriteHobbyValue.text = selectedEntry.favoriteHobby
            binding.favoriteBooksValue.text = selectedEntry.booksOrMovies
            binding.favoriteSportOrGameValue.text = selectedEntry.sportOrGame
            binding.favoriteDrawingOrPaintingValue.text = selectedEntry.drawingOrPainting
            binding.favoriteFunHobbyValue.text = selectedEntry.funHobby

            // Load the profile image using Glide
            Glide.with(this).load(selectedEntry.avatarId).into(binding.profileImg)
        }
    }
}
