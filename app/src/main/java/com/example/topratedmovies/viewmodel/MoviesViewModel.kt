package com.example.topratedmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.topratedmovies.api.MoviesApi
import com.example.topratedmovies.model.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {

    val moviesApi: MoviesApi = MoviesApi()
    var allTopMovies: MutableLiveData<Movies> = MutableLiveData()

    fun loadResults() {
        val apiCall = moviesApi.getTopMovies("85cd01088c47c4b5e700ab0ee81b6d69")

        apiCall.enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {
            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {

                response.isSuccessful.let {
                    val resultList = Movies(response.body()?.results ?: emptyList())
                    allTopMovies.value = resultList
                }
            }
        })

    }

}