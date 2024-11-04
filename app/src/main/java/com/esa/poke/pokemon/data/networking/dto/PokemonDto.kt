package com.esa.poke.pokemon.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    val name: String,
    val url: String,
)