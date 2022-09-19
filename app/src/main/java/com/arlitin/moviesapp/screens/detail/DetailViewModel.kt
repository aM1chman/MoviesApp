package com.arlitin.moviesapp.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arlitin.moviesapp.REALIZATION
import com.arlitin.moviesapp.data.room.repository.MoviesRepositoryRealization
import com.arlitin.moviesapp.model.MovieItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    fun insert(movieItemModel: MovieItemModel, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.insertMovie(movieItemModel){
                onSuccess()
            }
        }
    }

    fun delete(movieItemModel: MovieItemModel, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.deleteMovie(movieItemModel){
                onSuccess()
            }
        }
    }
}