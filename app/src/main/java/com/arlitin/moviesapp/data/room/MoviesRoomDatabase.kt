package com.arlitin.moviesapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arlitin.moviesapp.data.room.dao.MoviesDao
import com.arlitin.moviesapp.data.room.repository.MoviesRepositoryRealization
import com.arlitin.moviesapp.model.MovieItemModel

@Database(entities = [MovieItemModel::class], version = 1)
abstract class MoviesRoomDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MoviesDao

    companion object{
        private var database: MoviesRoomDatabase? = null

        fun getInstance(context: Context): MoviesRoomDatabase{
            return if(database == null){
                database = Room.databaseBuilder(
                    context,
                    MoviesRoomDatabase::class.java,
                    "db"
                ).build()
                database as MoviesRoomDatabase
            } else {
                database as MoviesRoomDatabase
            }
        }
    }
}