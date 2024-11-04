package com.esa.poke.pokemon.data.networking

import com.esa.poke.BuildConfig
import com.esa.poke.core.data.networking.constructUrl
import com.esa.poke.core.data.networking.safeCall
import com.esa.poke.core.domain.util.NetworkError
import com.esa.poke.core.domain.util.Result
import com.esa.poke.core.domain.util.map
import com.esa.poke.pokemon.data.mappers.toPoke
import com.esa.poke.pokemon.data.networking.dto.PokemonResponseDto
import com.esa.poke.pokemon.domain.Pokemon
import com.esa.poke.pokemon.domain.PokemonDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemotePokeDataSource(
    private val httpClient: HttpClient
): PokemonDataSource {

    override suspend fun getPokemons(limit: Int, offset: Int): Result<List<Pokemon>, NetworkError> {
        return safeCall<PokemonResponseDto> {
            httpClient.get(
                urlString = constructUrl("${BuildConfig.BASE_URL}?offset=$offset&limit=$limit")
            )
        }.map { response ->
            response.results.map { it.toPoke() }
        }
    }
}