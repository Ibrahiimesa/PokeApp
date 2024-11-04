package com.esa.poke.pokemon.presentation.poke_list

import com.esa.poke.pokemon.presentation.models.PokeUi

sealed interface PokemonListAction {
    data class OnPokeClick(val pokeUi: PokeUi): PokemonListAction
    object LoadMore : PokemonListAction
}