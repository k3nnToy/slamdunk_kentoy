package com.example.slamdunks

object SlambookRepository {
    private val slambooks = mutableListOf<SlambookEntry>()  // List that holds all users
    // Function to add a user to the repository
    fun addSlambook(slambook: SlambookEntry) {
        slambooks.add(slambook)  // Adds the new user to the list
    }

    // Function to get all users
    fun getSlambooks(): List<SlambookEntry> = slambooks

    // Function to delete a user from the repository
    fun deleteSlambook(slambook: SlambookEntry) {
        slambooks.remove(slambook)  // Removes the user from the list

    }
}

