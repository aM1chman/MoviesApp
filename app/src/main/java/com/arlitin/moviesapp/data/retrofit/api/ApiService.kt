package com.arlitin.moviesapp.data.retrofit.api

import com.arlitin.moviesapp.model.MoviesModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("3/movie/popular?api_key=d57d2df2c4666aac85327adace9aac60&language=en-US&page=1")
    suspend fun getPopularMovie(): Response<MoviesModel>
}