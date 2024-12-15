package com.example.slamdunks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.slamdunks.databinding.ActivityGetUsernameBinding

class GetUsernameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetUsernameBinding
    private var selectedAvatar: Avatar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetUsernameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the RecyclerView for avatar selection
        val avatars = listOf(
            Avatar(R.drawable.boy1),
            Avatar(R.drawable.boy2),
            Avatar(R.drawable.boy3),
            Avatar(R.drawable.girl1),
            Avatar(R.drawable.girl2),
            Avatar(R.drawable.girl3)
        )

        val adapter = AvatarAdapter(avatars) { avatar ->
            selectedAvatar = avatar
            binding.selectedAvatarImageView.setImageResource(avatar.imageResId)
        }

        // Use GridLayoutManager for displaying avatars in a grid
        val gridLayoutManager = GridLayoutManager(this, 3)  // 3 columns
        binding.avatarRecyclerView.layoutManager = gridLayoutManager
        binding.avatarRecyclerView.adapter = adapter

        // Create button to proceed to HomepageActivity
        binding.createButton.setOnClickListener {
            val username = binding.UsernameInput.text.toString()

            if (username.isNotEmpty() && selectedAvatar != null) {
                // Log the username and avatar for debugging
                Log.d("GetUsernameActivity", "Username: $username, Avatar ResId: ${selectedAvatar?.imageResId}")

                // Create a SlambookUser object
                val user = SlambookUser(username, selectedAvatar!!.imageResId)

                // Pass the user object to HomepageActivity via Intent
                val intent = Intent(this, HomepageActivity::class.java).apply {
                    putExtra("USER", user)  // Pass the user object
                }
                startActivity(intent)
            } else {
                // Show an error if the username or avatar is not selected
                Toast.makeText(this, "Please select an avatar and enter a username.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
