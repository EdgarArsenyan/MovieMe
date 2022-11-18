package com.example.movieme.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Search
import kotlinx.android.synthetic.main.item_movie_layout.view.*

class MovieVH(itemView: View): RecyclerView.ViewHolder(itemView)  {

    fun bind(model: Search){
        itemView.tvMovieTitle.text = model.Title
        itemView.tvRealise.text = model.Year
        itemView.tvItemType.text = model.Type
        Glide.with(itemView.context)
            .load(model.Poster)
            .into(itemView.ivListItem)
    }
}
