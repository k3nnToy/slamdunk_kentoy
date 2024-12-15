package com.example.slamdunks

import java.io.Serializable

data class SlambookUser(
    val username: String,  // User's username
    val avatarResId: Int   // Resource ID for the user's avatar
) : Serializable
