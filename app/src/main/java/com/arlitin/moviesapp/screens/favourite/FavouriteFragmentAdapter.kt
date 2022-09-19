package com.arlitin.moviesapp.screens.favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arlitin.moviesapp.IMAGE_URL
import com.arlitin.moviesapp.MAIN
import com.arlitin.moviesapp.R
import com.arlitin.moviesapp.model.MovieItemModel
import com.arlitin.moviesapp.screens.main.MainFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class FavouriteFragmentAdapter: RecyclerView.Adapter<FavouriteFragmentAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private var listMovies = emptyList<MovieItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.itemTitle.text = listMovies[position].title
        holder.itemView.itemDate.text = listMovies[position].release_date
        Glide.with(MAIN)
            .load("$IMAGE_URL${listMovies[position].poster_path}")
            .centerCrop()
            . placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.itemView.imgItem)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun setList(list: List<MovieItemModel>){
        listMovies = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            FavouriteFragment.clickMovie(listMovies[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}