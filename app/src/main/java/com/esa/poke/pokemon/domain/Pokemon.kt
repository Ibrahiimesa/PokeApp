package com.esa.poke.pokemon.domain

import kotlinx.serialization.SerialName

data class Pokemon(
    @SerialName(value = "name")
    val nameField: String,
    @SerialName(value = "url") val url: String,
)
