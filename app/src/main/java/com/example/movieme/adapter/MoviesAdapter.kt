package com.example.movieme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Search
import com.example.movieme.R

class MoviesAdapter(private val listener: ItemListener) : RecyclerView.Adapter<MovieVH>() {

    interface ItemListener {
        fun onClick(id: String)
    }

    private var listOfMovies = arrayListOf<Search>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_layout, parent, false)
        return MovieVH(item)
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.apply {
            bind(listOfMovies[position])
            itemView.setOnClickListener { listener.onClick(listOfMovies[position].imdbID) }
        }
    }

    fun setItems(models: List<Search>) {
        listOfMovies.clear()
        listOfMovies.addAll(models)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listOfMovies.size
}