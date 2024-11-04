package com.esa.poke.pokemon.data.mappers

import com.esa.poke.pokemon.data.networking.dto.PokemonDto
import com.esa.poke.pokemon.domain.Pokemon

fun PokemonDto.toPoke(): Pokemon {
    return Pokemon(
        nameField = name,
        url = url
    )
}