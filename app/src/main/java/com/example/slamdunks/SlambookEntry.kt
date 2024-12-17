package com.example.slamdunks

import java.io.Serializable

data class SlambookEntry(
    val fullname: String = "",
    val age: Int,
    val gender: String = "",
    val address: String = "",
    var avatarId: Int = R.drawable.boy1,

    // New fields to store favorite things and hobbies
    var favoriteColor: String = "",
    var favoriteFood: String = "",
    var favoriteSport: String = "",
    var favoriteOnlineGames: String = "",

    var favoriteHobby: String = "",
    var booksOrMovies: String = "",
    var sportOrGame: String = "",
    var drawingOrPainting: String = "",
    var funHobby: String = ""
) : Serializable
