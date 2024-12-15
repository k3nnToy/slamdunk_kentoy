package com.example.slamdunks

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
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

        // Retrieve the username and avatar resource ID from the intent
        val username = intent.getStringExtra("USERNAME")
        val avatarResId = intent.getIntExtra("AVATAR_RES_ID", -1)

        // Update the UI with the username and avatar
        if (username != null) {
            binding.nameText.text = username
        }

        if (avatarResId != -1) {
            Glide.with(this).load(avatarResId).into(binding.profile)
        }

        // Add all users to avatarList and userListFiltered
        avatarList.addAll(Form1Activity.userList)
        userListFiltered.addAll(avatarList)

        // Initialize the userAdapter with the filtered list and onAvatarClick callback
        userAdapter = SlambookAdapter(userListFiltered) { selectedUser ->
            // Handle avatar click
            Toast.makeText(this, "Clicked on ${selectedUser.fullname}", Toast.LENGTH_SHORT).show()
        }

        // Set up the RecyclerView with the userAdapter
        setupRecyclerView()

        // Floating action button to navigate to Form1Activity
        binding.fab.setOnClickListener {
            startActivity(Intent(this, Form1Activity::class.java))
            finish()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomepageActivity)
            adapter = userAdapter  // Set the userAdapter for the RecyclerView
            setHasFixedSize(true)  // Improves performance if size of the RecyclerView does not change
        }
    }

    // Method to add a user to the list and update the adapter
    private fun addUserList(user: SlambookEntry) {
        avatarList.add(user)
        userListFiltered.add(user)
        userAdapter.updateList(userListFiltered)  // Update the filtered list
    }
}
