package com.esa.poke.di

import com.esa.poke.core.data.networking.HttpClientFactory
import com.esa.poke.pokemon.domain.PokemonDataSource
import com.esa.poke.pokemon.data.networking.RemotePokeDataSource
import com.esa.poke.pokemon.presentation.poke_list.PokemonListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemotePokeDataSource).bind<PokemonDataSource>()

    viewModelOf(::PokemonListViewModel)
}