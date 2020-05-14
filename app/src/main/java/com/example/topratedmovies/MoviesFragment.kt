package com.example.topratedmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.topratedmovies.adapter.MoviesAdapter
import com.example.topratedmovies.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment() {

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        moviesAdapter = MoviesAdapter()
        recyclerMovies.apply {
            adapter = moviesAdapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        }
        observedViewModel()
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.loadResults()
    }

    fun observedViewModel(){

        moviesViewModel = ViewModelProvider(this)
            .get(MoviesViewModel::class.java)


        moviesViewModel.allTopMovies.observe(viewLifecycleOwner,
            Observer { myResult ->
                moviesAdapter.updateResultList(myResult.results)
            })


    }
}
