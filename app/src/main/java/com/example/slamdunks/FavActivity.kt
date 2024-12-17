package com.example.slamdunks

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.slamdunks.databinding.ActivityFavBinding

class FavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up Save button listener
        binding.btnSave.setOnClickListener {
            if (validateInputs()) {
                val slambookEntry = createSlambookEntry()
                SlambookRepository.addSlambook(slambookEntry)
                navigateToHomepage(slambookEntry)
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun validateInputs(): Boolean {
        return binding.etFavoriteColor.text.isNotBlank() &&
                binding.etFavoriteFood.text.isNotBlank() &&
                binding.FavoriteSports.text.isNotBlank() &&
                binding.etOnlineGames.text.isNotBlank() &&
                binding.etFavoriteHobby.text.isNotBlank() &&
                binding.etBooksOrMovies.text.isNotBlank() &&
                binding.etSportOrGame.text.isNotBlank() &&
                binding.etDrawingOrPainting.text.isNotBlank() &&
                binding.etFunHobby.text.isNotBlank()
    }

    // Function to create SlambookEntry object
    private fun createSlambookEntry(): SlambookEntry {
        return SlambookEntry(
            favoriteColor = binding.etFavoriteColor.text.toString(),
            favoriteFood = binding.etFavoriteFood.text.toString(),
            favoriteSport = binding.FavoriteSports.text.toString(),
            favoriteOnlineGames = binding.etOnlineGames.text.toString(),
            favoriteHobby = binding.etFavoriteHobby.text.toString(),
            booksOrMovies = binding.etBooksOrMovies.text.toString(),
            sportOrGame = binding.etSportOrGame.text.toString(),
            drawingOrPainting = binding.etDrawingOrPainting.text.toString(),
            funHobby = binding.etFunHobby.text.toString()
        )
    }

    private fun navigateToHomepage(slambookEntry: SlambookEntry) {
        val intent = Intent(this, HomepageActivity::class.java).apply {
            putExtra("slambookEntry", slambookEntry)
        }
        startActivity(intent)
    }
}
