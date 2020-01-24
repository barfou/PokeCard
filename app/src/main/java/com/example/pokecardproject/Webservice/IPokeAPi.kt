package com.example.pokecardproject.Webservice

import com.example.pokecardproject.Entity.PokemonInfo
import com.example.pokecardproject.Entity.Pokemons
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface IPokeAPI {

    /*
        @POST("/posts")
    @FormUrlEncoded
    Call<Post> savePost(@Field("title") String title,
                        @Field("body") String body,
                        @Field("userId") long userId);
     */


    /*@GET("changes/")
    fun loadChanges(@Query("q") status: String): Call<List<Change>>

    @GET("people/{id}")
    fun getUnPeople(@Path("id") userId: Int): Call<People>

    @GET("starships/{id}")
    fun getStarship(@Path("id") starshipId: Int): Call<Starship>*/

    //@GET("people/")
    //fun loadPeoples(): Call<Peoples>

    //http://192.168.240.66/index.php/pokemons/list
    //@GET("pokemons/list")
    //fun loadListPokemons(): Call<Pokemons>

    //@GET("pokemons/{url}")
    //fun loadPokemon (@Path("url") url: String?) : Call<PokemonInfo?>?

    // Call Direct à API Poke Card
    @GET("pokemon")
    fun loadListPokemons(): Call<Pokemons>

    @GET
    fun loadPokemon(@Url url: String?): Call<PokemonInfo?>?
}