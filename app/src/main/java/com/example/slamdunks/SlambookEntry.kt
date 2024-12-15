package com.example.slamdunks

import java.io.Serializable

data class SlambookEntry(
    val fullname: String,
    val age: Int,
    val gender: String,
    val address: String,
    var avatarId: Int = R.drawable.boy1,

    // New fields to store favorite things and hobbies
    var favoriteColor: String? = null,
    var favoriteFood: String? = null,
    var favoriteSport: String? = null,
    var favoriteOnlineGames: String? = null,

    var favoriteHobby: String? = null,
    var booksOrMovies: String? = null,
    var sportOrGame: String? = null,
    var drawingOrPainting: String? = null,
    var funHobby: String? = null
) : Serializable
