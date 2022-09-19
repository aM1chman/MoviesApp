package com.arlitin.moviesapp.data.retrofit

import com.arlitin.moviesapp.data.retrofit.api.RetrofitInstance
import com.arlitin.moviesapp.model.MoviesModel
import retrofit2.Response

class RetrofitRepository {
    suspend fun getMovies(): Response<MoviesModel>{
        return RetrofitInstance.api.getPopularMovie()
    }
}