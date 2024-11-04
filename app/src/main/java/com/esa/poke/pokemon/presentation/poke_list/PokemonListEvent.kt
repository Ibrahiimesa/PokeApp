package com.esa.poke.pokemon.presentation.poke_list

import com.esa.poke.core.domain.util.NetworkError

sealed interface PokemonListEvent {
    data class Error(val error: NetworkError): PokemonListEvent
}