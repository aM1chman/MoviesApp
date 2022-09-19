package com.arlitin.moviesapp.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.arlitin.moviesapp.IMAGE_URL
import com.arlitin.moviesapp.MAIN
import com.arlitin.moviesapp.R
import com.arlitin.moviesapp.SaveShared
import com.arlitin.moviesapp.databinding.FragmentDetailBinding
import com.arlitin.moviesapp.databinding.FragmentFavouriteBinding
import com.arlitin.moviesapp.databinding.FragmentMainBinding
import com.arlitin.moviesapp.model.MovieItemModel
import com.arlitin.moviesapp.screens.favourite.FavouriteFragmentViewModel
import com.arlitin.moviesapp.screens.main.MainFragmentAdapter
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {
    private var mBinding: FragmentDetailBinding? = null
    private val binding get() = mBinding!!
    lateinit var  currentMovie: MovieItemModel
    private var isFavourite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        currentMovie = arguments?.getSerializable("movie") as MovieItemModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val valueBoolean = SaveShared.getFavourite(MAIN, currentMovie.id.toString())
        val viewModel = ViewModelProvider(this)
            .get(DetailViewModel::class.java)

        if(isFavourite != valueBoolean){
            binding.imageDetailFavourite
                .setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.imageDetailFavourite
                .setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

        Glide.with(MAIN)
            .load("$IMAGE_URL${currentMovie.poster_path}")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imgDetail)
        binding.tvTitle.text = currentMovie.title
        binding.tvDate.text = currentMovie.release_date
        binding.tvDesc.text = currentMovie.overview

        binding.imageDetailFavourite.setOnClickListener {
            isFavourite = if(isFavourite == valueBoolean){
                binding.imageDetailFavourite
                    .setImageResource(R.drawable.ic_baseline_favorite_24)
                SaveShared.setFavourite(MAIN, currentMovie.id.toString(), true)
                viewModel.insert(currentMovie){}
                true
            } else {
                binding.imageDetailFavourite
                    .setImageResource(R.drawable.ic_baseline_favorite_border_24)
                SaveShared.setFavourite(MAIN, currentMovie.id.toString(), false)
                viewModel.delete(currentMovie){}
                false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

}