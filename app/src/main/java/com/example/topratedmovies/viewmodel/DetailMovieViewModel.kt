package com.example.topratedmovies.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.topratedmovies.api.MoviesApi
import com.example.topratedmovies.model.movieID.MovieID
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieViewModel : ViewModel() {

    var moviesApi: MoviesApi = MoviesApi()

    var result: MutableLiveData<MovieID> = MutableLiveData()

    fun getMovie() = result

    fun loadDetail(movieId: Int) {
        val apiCall = moviesApi.detailMovie(movieId, "85cd01088c47c4b5e700ab0ee81b6d69")
        apiCall.enqueue(object : Callback<MovieID> {
            override fun onFailure(call: Call<MovieID>, t: Throwable) {
                Log.i("Error >>>>>>", "Loading Fail")
            }

            override fun onResponse(call: Call<MovieID>, response: Response<MovieID>) {
                Log.i("Success >>>>>>", "Loading success")
                response.isSuccessful.let {
                    var getMovie = response.body()
                    result.value = getMovie
                }
            }

        })
    }

}