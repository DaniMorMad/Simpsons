package edu.iesam.simpsons.features.simpsons.data.remote.api

import com.google.gson.annotations.SerializedName

data class SimpsonsApiModel(
    val count: Int,
    val pages: Int,
    val prev: String?,
    val next: String?,
    val results: List<CharacterModel>
)

data class CharacterModel(
    val id: Int,
    val age: Int?,
    val birthdate: String?,
    val gender: String,
    val name: String,
    val occupation: String,
    @SerializedName("portrait_path") val imageUrl: String,
    val phrases: List<String>
)
