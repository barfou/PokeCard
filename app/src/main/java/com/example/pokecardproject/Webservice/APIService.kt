package com.example.pokecardproject.Webservice

import com.example.pokecardproject.*
import com.example.pokecardproject.Entity.PokemonBase
import com.example.pokecardproject.Entity.PokemonInfo
import com.example.pokecardproject.Entity.Pokemons
import com.example.pokecardproject.Listener.LoadedListener
import com.google.gson.GsonBuilder

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService(val activity: LoadedListener) {

    var Base_URL: String
    lateinit var retrofit: Retrofit

    init {
        Base_URL = "https://pokeapi.co/api/v2/"
        //Base_URL = "http://192.168.240.66/index.php/"
        start()
    }

    fun start() {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun GetListPokemons() {
        val pokeApi = retrofit.create<IPokeAPI>(IPokeAPI::class.java!!)
        val call = pokeApi.loadListPokemons()
        call.enqueue(object : Callback<Pokemons> {

            override fun onResponse(call: Call<Pokemons>, response: Response<Pokemons>) {

                if (response.body() != null) {

                    val pokemons: Pokemons = response.body()
                    ArrayList<PokemonBase>(pokemons.listePokemon).forEach { pokemon ->
                        MainActivity.listPokemons.add(
                            pokemon
                        )
                    }
                    activity.onListPokemonLoaded()
                }
            }

            override fun onFailure(call: Call<Pokemons>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun GetPokemon(url :String){
        val pokeApi = retrofit.create<IPokeAPI>(IPokeAPI::class.java!!)
        val call = pokeApi.loadPokemon(url)
        call!!.enqueue(object : Callback<PokemonInfo?> {

            override fun onResponse(call: Call<PokemonInfo?>?, response: Response<PokemonInfo?>?) {

                if (response != null) {

                    val pokemonInfo: PokemonInfo? = response.body()
                    activity.onPokemonInfoLoaded(pokemonInfo)
                }
            }
            override fun onFailure(call: Call<PokemonInfo?>?, t: Throwable?) {
                activity.onPokemonInfoLoaded(null)
            }
        })
    }
}
