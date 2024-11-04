package com.esa.poke.pokemon.presentation.poke_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.esa.poke.pokemon.presentation.poke_list.component.PokemonListItem

@Composable
fun PokemonScreen(
    state: PokemonListState,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    if(state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 columns
            verticalArrangement = Arrangement.spacedBy(16.dp), // Spacing between rows
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Spacing between columns
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(state.pokemons) { pokeUi ->
                PokemonListItem(
                    pokeUi = pokeUi,
                    onClick = { /* TODO: Handle click */ },
                    modifier = Modifier
                )
            }
            if (state.isLoadingMore) {
                item {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
            }

        }
        LaunchedEffect(state.offset) {
            if (state.hasMore) {
                onLoadMore()
            }
        }
    }
}