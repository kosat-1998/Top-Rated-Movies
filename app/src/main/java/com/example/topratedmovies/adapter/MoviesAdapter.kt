package com.example.topratedmovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.topratedmovies.R
import com.example.topratedmovies.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movies.view.*

class MoviesAdapter(var resultList: List<Result> = ArrayList()) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun moviesBind(result: Result) {
            itemView.textId.text = result.title
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${result.poster_path}")
                .placeholder(R.drawable.loading)
                .into(itemView.imageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        var myView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return MoviesViewHolder(myView)
    }

    override fun getItemCount(): Int {

        return resultList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.moviesBind(resultList[position])
    }


    fun updateResultList(resultList: List<Result>) {
        this.resultList = resultList
        notifyDataSetChanged()
    }
}