package com.esa.poke.pokemon.presentation.poke_list

import androidx.compose.runtime.Immutable
import com.esa.poke.pokemon.presentation.models.PokeUi

@Immutable
data class PokemonListState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val pokemons: List<PokeUi> = emptyList(),
    val selectedCoin: PokeUi? = null,
    val offset: Int = 20,
    val limit: Int = 20,
    val hasMore: Boolean = true
)