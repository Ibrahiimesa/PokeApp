package com.esa.poke.pokemon.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponseDto(
    val results: List<PokemonDto>
)