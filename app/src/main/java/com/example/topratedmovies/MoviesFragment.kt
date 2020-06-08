package com.example.topratedmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.topratedmovies.adapter.MoviesAdapter
import com.example.topratedmovies.model.Result
import com.example.topratedmovies.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment(), MoviesAdapter.ClickListener {

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
        moviesAdapter.setClick(this)
        recyclerMovies.apply {
            adapter = moviesAdapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        }
        observedViewModel()             //get
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.loadResults()           //load or set
    }

    private fun observedViewModel() {

        moviesViewModel = ViewModelProvider(this)
            .get(MoviesViewModel::class.java)


        moviesViewModel.allTopMovies.observe(viewLifecycleOwner,
            Observer { myResult ->
                moviesAdapter.updateResultList(myResult.results)
            })


    }

    override fun onClick(result: Result) {

        Toast.makeText(context, "${result.id}", Toast.LENGTH_LONG).show()
        var movieId = result.id
        var action = MoviesFragmentDirections.actionMoviesFragmentToDetailFragment(movieId)
        findNavController().navigate(action)
    }
}
