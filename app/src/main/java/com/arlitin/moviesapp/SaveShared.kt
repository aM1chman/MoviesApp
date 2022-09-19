package com.arlitin.moviesapp

import android.content.Context
import android.preference.PreferenceManager

@Suppress("DEPRECATION")
class SaveShared {
    companion object{
        fun setFavourite(context: Context?, key: String, value: Boolean){
            val setFavouriteShared = PreferenceManager
                .getDefaultSharedPreferences(context)
            setFavouriteShared.edit().putBoolean(key, value).apply()
        }

        fun getFavourite(context: Context?, key: String): Boolean{
            val getFavouriteShared = PreferenceManager
                .getDefaultSharedPreferences(context)
            return getFavouriteShared.getBoolean(key, false)
        }
    }
}