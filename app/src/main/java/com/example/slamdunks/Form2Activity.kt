package com.example.slamdunks

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.slamdunks.databinding.ActivityFavoriteBinding

class Form2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var slambookEntry: SlambookEntry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the SlambookEntry object passed from the previous activity
        slambookEntry = intent.getSerializableExtra("slambookEntry") as SlambookEntry

        // Populate the fields with the data from the SlambookEntry
        displayFavoriteData()
        displayHobbyData()

        // Handle the edit button click to show the Favorite Dialog
        binding.igmFav.setOnClickListener {
            val favoriteDialogFragment = FavoriteDialogFragment.newInstance(slambookEntry)
            favoriteDialogFragment.show(supportFragmentManager, favoriteDialogFragment.tag)
        }

        // Handle the edit button click to show the Hobby Dialog
        binding.imgHob.setOnClickListener {
            val hobbyDialogFragment = HobbyDialogFragment.newInstance(slambookEntry)
            hobbyDialogFragment.show(supportFragmentManager, hobbyDialogFragment.tag)
        }

        // Save Button logic to update the repository and navigate to HomepageActivity
        binding.Save.setOnClickListener {
            // Update the repository with the updated SlambookEntry
            SlambookRepository.addSlambook(slambookEntry)

            // Show a confirmation message
            Toast.makeText(this, "Slambook updated!", Toast.LENGTH_SHORT).show()

            // Start HomepageActivity without passing the updated data
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)

            // Finish the current activity so the user can't go back to it
            finish()
        }
    }

    // Display the favorite data
    private fun displayFavoriteData() {
        binding.favoriteColorValue.text = "Favorite Color: ${slambookEntry.favoriteColor.takeIf { it.isNotEmpty() } ?: "Not provided"}"
        binding.favoriteFoodValue.text = "Favorite Food: ${slambookEntry.favoriteFood.takeIf { it.isNotEmpty() } ?: "Not provided"}"
        binding.favoriteSportValue.text = "Favorite Sport: ${slambookEntry.favoriteSport.takeIf { it.isNotEmpty() } ?: "Not provided"}"
        binding.favoriteGamesValue.text = "Favorite Online Games: ${slambookEntry.favoriteOnlineGames.takeIf { it.isNotEmpty() } ?: "Not provided"}"
    }

    // Display the hobby data
    private fun displayHobbyData() {
        binding.favoriteHobbyValue.text = "Favorite Hobby: ${slambookEntry.favoriteHobby.takeIf { it.isNotEmpty() } ?: "Not provided"}"
        binding.booksOrMoviesValue.text = "Books or Movies: ${slambookEntry.booksOrMovies.takeIf { it.isNotEmpty() } ?: "Not provided"}"
        binding.sportOrGameValue.text = "Sport or Game: ${slambookEntry.sportOrGame.takeIf { it.isNotEmpty() } ?: "Not provided"}"
        binding.drawingOrPaintingValue.text = "Drawing or Painting: ${slambookEntry.drawingOrPainting.takeIf { it.isNotEmpty() } ?: "Not provided"}"
        binding.funHobbyValue.text = "Fun Hobby: ${slambookEntry.funHobby.takeIf { it.isNotEmpty() } ?: "Not provided"}"
    }

    // Method to update the SlambookEntry after editing the favorite data
    fun onFavoriteDataUpdated(updatedEntry: SlambookEntry) {
        slambookEntry = updatedEntry
        // Re-populate the data for favorites
        displayFavoriteData()
        Toast.makeText(this, "Favorites updated!", Toast.LENGTH_SHORT).show()
    }

    // Method to update the SlambookEntry after editing the hobby data
    fun onHobbyDataUpdated(updatedEntry: SlambookEntry) {
        slambookEntry = updatedEntry
        // Re-populate the data for hobbies
        displayHobbyData()
        Toast.makeText(this, "Hobbies updated!", Toast.LENGTH_SHORT).show()
    }
}
