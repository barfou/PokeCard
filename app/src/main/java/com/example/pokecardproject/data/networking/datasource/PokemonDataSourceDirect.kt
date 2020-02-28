package com.example.pokecardproject.data.networking.datasource

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.pokecardproject.data.model.PokemonBase
import com.example.pokecardproject.data.networking.api.PokeAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonDataSourceDirect private constructor(
    private val api: PokeAPI,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, PokemonBase>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PokemonBase>
    ) {
        scope.launch(Dispatchers.IO) {
            try {
                val response = api.getAllPokemonDirect(offset = FIRST_KEY, limit = 20).run {
                    if (this.isSuccessful) this.body()
                        ?: throw IllegalStateException("Body is null")
                    else throw IllegalStateException("Response is not successful")
                }
                if (params.placeholdersEnabled) callback.onResult(
                    response.results,
                    0,
                    response.count,
                    null,
                    if (response.next != null) FIRST_KEY + 20 else null
                ) else callback.onResult(
                    response.results,
                    null,
                    if (response.next != null) FIRST_KEY + 20 else null
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PokemonBase>) {
        scope.launch(Dispatchers.IO) {
            try {
                val response = api.getAllPokemonDirect(offset = FIRST_KEY, limit = 20).run {
                    if (this.isSuccessful) this.body()
                        ?: throw IllegalStateException("Body is null")
                    else throw IllegalStateException("Response is not successful : code = ${this.code()}")
                }
                callback.onResult(
                    response.results,
                    if (response.next != null) params.key + 20 else null
                )
            } catch (e: Exception) {
                Log.e(TAG, "loadInitial: ", e)
            }
        }
    }

    // This method will not be used in this app
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PokemonBase>) = Unit


    class Factory(
        private val api: PokeAPI,
        private val scope: CoroutineScope
    ) : DataSource.Factory<Int, PokemonBase>() {
        override fun create(): DataSource<Int, PokemonBase> =
            PokemonDataSourceDirect(
                api, scope
            )
    }

    companion object {
        private const val TAG: String = "PokemondataSource"
        private const val FIRST_KEY = 0
    }

}