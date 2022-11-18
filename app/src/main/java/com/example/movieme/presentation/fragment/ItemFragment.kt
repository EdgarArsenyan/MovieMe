package com.example.movieme.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.data.util.AppConstants.EMPTY_STRING
import com.example.domain.model.MovieModel
import com.example.movieme.R
import com.example.movieme.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_item_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemFragment: Fragment(R.layout.fragment_item_layout) {

    private val viewModel by viewModel<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        clickHandle()

    }

    private fun clickHandle() {
        toolbarItem.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initViewModel(){
            viewModel.loadById(imdbId, plot = "short").also {
                viewModel.movieResult.observe(viewLifecycleOwner){
                    initView(it)
                }
            }
    }

    private fun initView(movieModel: MovieModel){
        Glide.with(requireContext())
            .load(movieModel.Poster)
            .into(ivItem)
        tvItemTitle.text = movieModel.Title
        tvItemRealise.text = movieModel.Year
        tvItemType.text = movieModel.Type
    }

    companion object{
        var imdbId = EMPTY_STRING
    }
}