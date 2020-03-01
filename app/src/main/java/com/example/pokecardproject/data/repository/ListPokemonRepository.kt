package com.example.pokecardproject.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pokecardproject.BuildConfig
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.data.networking.BaseUrlHolder
import com.example.pokecardproject.data.networking.HttpClientManager
import com.example.pokecardproject.data.networking.api.PokeAPI
import com.example.pokecardproject.data.networking.createApi
import com.example.pokecardproject.data.networking.datasource.PokemonDataSource
import com.example.pokecardproject.data.networking.datasource.PokemonDataSourceDirect
import kotlinx.coroutines.CoroutineScope

class ListPokemonRepositoryImpl(
    private val api: PokeAPI
) : ListPokemonRepository {

    // Config for pagination
    private val paginationConfig = PagedList.Config
        .Builder()
        // If you set true you will have to catch
        // the place holder case in the adapter
        .setEnablePlaceholders(false)
        .setPageSize(20)
        .build()

    override fun getPaginatedList(scope: CoroutineScope): LiveData<PagedList<PokemonBase>> {

        if (BaseUrlHolder.baseUrl == BuildConfig.BASE_URL_SRV_LOCAL) {
            return LivePagedListBuilder(
                PokemonDataSource.Factory(api, scope),
                paginationConfig
            ).build()
        } else {
            return LivePagedListBuilder(
                PokemonDataSourceDirect.Factory(api, scope),
                paginationConfig
            ).build()
        }
    }
}

interface ListPokemonRepository {

    fun getPaginatedList(scope: CoroutineScope): LiveData<PagedList<PokemonBase>>

    companion object {
        /**
         * Singleton for the interface [PokeRepository]
         */
        val instance: ListPokemonRepository by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the instance,
            // then, the reference will be stored in the value `instance`
            ListPokemonRepositoryImpl(HttpClientManager.instance.createApi())
        }
    }
}