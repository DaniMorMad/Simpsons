package edu.iesam.simpsons.features.simpsons.data.remote.api

import edu.iesam.simpsons.features.simpsons.domain.Character

fun CharacterModel.toModel(): Character {
    return Character(this.id,
        this.name,
        this.occupation,
        "https://cdn.thesimpsonsapi.com/500" + this.imageUrl)
}