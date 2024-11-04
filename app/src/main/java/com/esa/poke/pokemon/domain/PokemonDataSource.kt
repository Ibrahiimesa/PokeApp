package com.esa.poke.pokemon.domain

import com.esa.poke.core.domain.util.NetworkError
import com.esa.poke.core.domain.util.Result


interface PokemonDataSource {
    suspend fun getPokemons(limit: Int, offset: Int): Result<List<Pokemon>, NetworkError>
}