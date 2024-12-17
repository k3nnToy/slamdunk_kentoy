package com.example.slamdunks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.slamdunks.databinding.ActivityFavBinding

class FavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            // Create SlambookEntry object from user input
            val slambookEntry = SlambookEntry(
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

            // Save to repository (optional)
            SlambookRepository.addSlambook(slambookEntry)

            // Pass SlambookEntry object to HomepageActivity via Intent
            val intent = Intent(this, HomepageActivity::class.java).apply {
                putExtra("slambookEntry", slambookEntry)
            }

            startActivity(intent)
        }
    }
}
