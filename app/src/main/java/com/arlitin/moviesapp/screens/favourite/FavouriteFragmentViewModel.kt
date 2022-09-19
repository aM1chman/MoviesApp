package com.arlitin.moviesapp.screens.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arlitin.moviesapp.REALIZATION
import com.arlitin.moviesapp.data.room.repository.MoviesRepositoryRealization
import com.arlitin.moviesapp.model.MovieItemModel

class FavouriteFragmentViewModel: ViewModel() {

    fun getAllMovies(): LiveData<List<MovieItemModel>>{
        return REALIZATION.allMovies
    }
}
