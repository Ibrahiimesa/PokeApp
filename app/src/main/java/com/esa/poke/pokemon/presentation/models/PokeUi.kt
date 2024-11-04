package com.esa.poke.pokemon.presentation.models

import com.esa.poke.pokemon.domain.Pokemon


data class PokeUi(
    val name: String,
    val imageUrl: String,
    val id: String,
)

fun Pokemon.toPokeUi(): PokeUi {
    val index = url.split("/".toRegex()).dropLast(1).last()
    val formattedId = String.format("%03d", index.toInt())

    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
            "pokemon/other/official-artwork/$index.png"

    return PokeUi(
        name = nameField.replaceFirstChar { it.uppercase() },
        imageUrl = imageUrl,
        id = formattedId
    )
}