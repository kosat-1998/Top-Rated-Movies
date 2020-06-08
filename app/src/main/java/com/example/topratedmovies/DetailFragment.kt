package com.example.topratedmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.topratedmovies.viewmodel.DetailMovieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_movies.view.*

class DetailFragment : Fragment() {

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private var movieId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailMovieViewModel = ViewModelProvider(this).get(DetailMovieViewModel::class.java)
        var id = arguments?.let { DetailFragmentArgs.fromBundle(it) }
        movieId = id?.id ?: 0

        obserView()
    }

    override fun onResume() {
        super.onResume()

        detailMovieViewModel.loadDetail(movieId)
    }


    private fun obserView() {
        detailMovieViewModel.getMovie().observe(viewLifecycleOwner,
            Observer { result ->

                Picasso.get().load("https://image.tmdb.org/t/p/w500${result.poster_path}")
                    .placeholder(R.drawable.loading)
                    .into(poster_path)
                original_title.text = result.original_title
                overview.text = result.overview
                vote_average.text = result.vote_average.toString()
                release_date.text = result.release_date

            })
    }
}