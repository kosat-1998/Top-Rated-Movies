package com.example.topratedmovies.api

import com.example.topratedmovies.model.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApi {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    }

    private val apiInterface: ApiInterface


    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    fun getTopMovies(apiKey: String): Call<Movies> = apiInterface.getTopRatedMovie(apiKey)
    fun detailMovie(movieId : Int , apiKey: String) = apiInterface.detailMovie(movieId,apiKey)
}