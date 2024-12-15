package com.example.slamdunks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.slamdunks.FavoriteDialogFragment
import com.example.slamdunks.databinding.ActivityFavoriteBinding
import com.example.slamdunks.Form1Activity
import com.example.slamdunks.HomepageActivity
import com.example.slamdunks.HobbyDialogFragment


class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.igmFav.setOnClickListener {
            try {
                val favoriteDialogFragment = FavoriteDialogFragment.newInstance()
                favoriteDialogFragment.show(supportFragmentManager, favoriteDialogFragment.tag)
            } catch (e: Exception) {
                Log.e("FavoritesActivity", "Error showing dialog", e)
            }
        }

        binding.imgHob.setOnClickListener{
            try {
                val hobbyDialogFragment = HobbyDialogFragment.newInstance()
                hobbyDialogFragment.show(supportFragmentManager, hobbyDialogFragment.tag)
            } catch (e: Exception) {
                Log.e("HobbiesActivity", "Error showing dialog", e)
            }
        }

        // In FavoritesActivity:
        binding.Save.setOnClickListener {
            val nickname = intent.getStringExtra("fullname") // Retrieve nickname
            val intent = Intent(this, HomepageActivity::class.java)
            intent.putExtra("fullname", nickname) // Pass nickname to HomeActivity
            startActivity(intent)
            finish() // Optionally finish this activity
        }

        binding.backButton.setOnClickListener{
            startActivity(Intent(this,Form1Activity::class.java))
            finish()
        }

    }
}
