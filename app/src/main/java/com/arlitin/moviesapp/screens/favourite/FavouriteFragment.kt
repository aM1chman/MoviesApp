package com.arlitin.moviesapp.screens.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.arlitin.moviesapp.MAIN
import com.arlitin.moviesapp.R
import com.arlitin.moviesapp.databinding.FragmentFavouriteBinding
import com.arlitin.moviesapp.databinding.FragmentMainBinding
import com.arlitin.moviesapp.model.MovieItemModel
import com.arlitin.moviesapp.screens.main.MainFragmentAdapter
import com.arlitin.moviesapp.screens.main.MainFragmentViewModel

class FavouriteFragment : Fragment() {
    private var mBinding: FragmentFavouriteBinding? = null
    private val binding get() = mBinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { FavouriteFragmentAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)
            .get(FavouriteFragmentViewModel::class.java)
        recyclerView = binding.rvFavourite
        recyclerView.adapter = adapter
        viewModel.getAllMovies().observe(viewLifecycleOwner){list ->
            adapter.setList(list.asReversed())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    companion object{
        fun clickMovie(model: MovieItemModel){
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController
                .navigate(R.id.action_favouriteFragment_to_detailFragment, bundle)
        }
    }

}