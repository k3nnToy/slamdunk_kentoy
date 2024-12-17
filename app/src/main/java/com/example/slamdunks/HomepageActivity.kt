package com.example.slamdunks

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slamdunks.databinding.ActivityHomepageBinding

class HomepageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomepageBinding
    private lateinit var userAdapter: SlambookAdapter
    private val avatarList = mutableListOf<SlambookEntry>()
    private val userListFiltered = mutableListOf<SlambookEntry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve and display the saved user data
        val user = getSavedUserData()

        if (user != null) {
            // Set the avatar and username in the appropriate views
            val profileImageView: ImageView = binding.profile  // Your ImageView for profile picture
            val nameTextView: TextView = binding.nameText      // Your TextView for name

            profileImageView.setImageResource(user.avatarResId)  // Set avatar image
            nameTextView.text = user.username                    // Set username
        } else {
            // If no user data is available, show a default message
            Toast.makeText(this, "No user data available", Toast.LENGTH_SHORT).show()
        }

        // Add all users to avatarList and userListFiltered
        avatarList.addAll(Form1Activity.userList)
        userListFiltered.addAll(avatarList)

        // Initialize the userAdapter with the filtered list and onAvatarClick callback
        userAdapter = SlambookAdapter(userListFiltered,
            onAvatarClick = { selectedUser ->
                // Navigate to DisplayActivity and pass the selected user data
                val intent = Intent(this, DisplayActivity::class.java).apply {
                    putExtra("slambookEntry", selectedUser) // Pass the SlambookEntry object
                }
                startActivity(intent)
            },
            onEditClick = { selectedUser ->
                // Handle the edit button click
                Toast.makeText(this, "Edit ${selectedUser.fullname}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Form1Activity::class.java).apply {
                    putExtra("slambookEntry", selectedUser)  // Pass the selected SlambookEntry
                }
                startActivity(intent)
            },
            onDeleteClick = { selectedUser ->
                // Handle the delete button click
                SlambookRepository.deleteSlambook(selectedUser)
                avatarList.remove(selectedUser)
                userListFiltered.remove(selectedUser)
                userAdapter.updateList(userListFiltered)
                Toast.makeText(this, "Deleted ${selectedUser.fullname}", Toast.LENGTH_SHORT).show()
            }
        )

        // Set up the RecyclerView with the userAdapter
        setupRecyclerView()

        // Floating action button to navigate to Form1Activity
        binding.fab.setOnClickListener {
            startActivity(Intent(this, Form1Activity::class.java))
            finish() // Optionally finish the current activity
        }
    }

    // Function to retrieve the saved user data from SharedPreferences
    private fun getSavedUserData(): SlambookUser? {
        val sharedPreferences = getSharedPreferences("SLAMBOOK_PREFS", MODE_PRIVATE)
        val username = sharedPreferences.getString("username", null)
        val avatarResId = sharedPreferences.getInt("avatarResId", -1)

        return if (username != null && avatarResId != -1) {
            SlambookUser(username, avatarResId)
        } else {
            null
        }
    }

    // Set up the RecyclerView
    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomepageActivity)
            adapter = userAdapter  // Set the userAdapter for the RecyclerView
            setHasFixedSize(true)  // Improves performance if size of the RecyclerView does not change
        }
    }
}
