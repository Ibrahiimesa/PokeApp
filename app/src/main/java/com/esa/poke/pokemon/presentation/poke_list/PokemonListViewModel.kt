package com.esa.poke.pokemon.presentation.poke_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esa.poke.core.domain.util.onError
import com.esa.poke.core.domain.util.onSuccess
import com.esa.poke.pokemon.domain.PokemonDataSource
import com.esa.poke.pokemon.presentation.models.toPokeUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val pokemonDataSource: PokemonDataSource
): ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state = _state
        .onStart { loadPokemons() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PokemonListState()
        )

    private val _events = Channel<PokemonListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: PokemonListAction) {
        when(action) {
            is PokemonListAction.OnPokeClick -> {

            }
            PokemonListAction.LoadMore -> {
                if (_state.value.hasMore && !_state.value.isLoading) {
                    loadMorePokemons()
                }
            }
        }
    }

    private fun loadPokemons(offset: Int = 0, isPaginating: Boolean = false) {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = !isPaginating && offset == 0,
                isLoadingMore = isPaginating
            )}

            pokemonDataSource
                .getPokemons(limit = _state.value.limit, offset = offset)
                .onSuccess { pokemons ->
                    _state.update { it.copy(
                        isLoading = false,
                        isLoadingMore = false,
                        pokemons = if (isPaginating) it.pokemons + pokemons.map { it.toPokeUi() }
                        else pokemons.map { it.toPokeUi() },
                        offset = offset + _state.value.limit,
                        hasMore = pokemons.size == _state.value.limit
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false, isLoadingMore = false) }
                    _events.send(PokemonListEvent.Error(error))
                }
        }
    }

    private fun loadMorePokemons() {
        loadPokemons(_state.value.offset, isPaginating = true)
    }

}