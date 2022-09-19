package com.arlitin.moviesapp.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arlitin.moviesapp.model.MovieItemModel

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movieItemModel: MovieItemModel)

    @Delete
    suspend fun delete(movieItemModel: MovieItemModel)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<MovieItemModel>>
}