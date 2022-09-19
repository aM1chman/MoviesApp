package com.arlitin.moviesapp.screens.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.arlitin.moviesapp.MAIN
import com.arlitin.moviesapp.R
import com.arlitin.moviesapp.databinding.FragmentMainBinding
import com.arlitin.moviesapp.model.MovieItemModel

class MainFragment : Fragment() {

    private var mBinding: FragmentMainBinding? = null
    private val binding get() = mBinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { MainFragmentAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)
            .get(MainFragmentViewModel::class.java)
        viewModel.initDatabase()
        recyclerView = binding.rvMain
        recyclerView.adapter = adapter
        try{
            viewModel.getMoviesRetrofit()
            viewModel.myMovies.observe(viewLifecycleOwner){list ->
                adapter.setList(list.body()!!.results)
            }
        }catch (e: Exception){
            Log.d("MyLog", "${e.message}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.itemFavourite -> {
                MAIN.navController.navigate(R.id.action_mainFragment_to_favouriteFragment)
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    companion object{
        fun clickMovie(model: MovieItemModel){
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController
                .navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }
}