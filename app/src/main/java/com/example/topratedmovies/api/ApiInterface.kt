package com.example.topratedmovies.api

import com.example.topratedmovies.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top_rated")
    fun getTopRatedMovie(

        //Query String parameter

        @Query ("api_key")
        apiKey: String

    ):Call<Movies>
}