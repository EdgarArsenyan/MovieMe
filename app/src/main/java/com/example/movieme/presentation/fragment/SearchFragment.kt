package com.example.movieme.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.util.AppConstants.EMPTY_STRING
import com.example.data.util.AppConstants.NOT_FOUND_DIALOG
import com.example.domain.model.Search
import com.example.movieme.R
import com.example.movieme.adapter.MoviesAdapter
import com.example.movieme.presentation.fragment.ItemFragment.Companion.imdbId
import com.example.movieme.util.hideKeyboard
import com.example.movieme.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_search_layout.*
import kotlinx.android.synthetic.main.search_menu_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_search_layout) {

    private val viewModel by viewModel<MovieViewModel>()
    private lateinit var moviesAdapter: MoviesAdapter
    private var searchList: MutableList<Search> = mutableListOf()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickHandleFromView()
        viewModel.searchResult.observe(viewLifecycleOwner) {
            if(it.Response.isNotEmpty()){
                searchList = it.Search as MutableList<Search>
                initRecycler()
            }
        }
    }

    private fun clickHandleFromView() {
        toolbarSearch.setNavigationOnClickListener {
            searchDialogLayout.isVisible = !searchDialogLayout.isVisible
            removeSearchParams()
        }

        btnApply.setOnClickListener {
            if (etSearchTitle.text.isNullOrEmpty().not()){
                viewModel.loadBySearch(
                    etSearchTitle.text.toString(),
                    if (etPage.text.isNullOrEmpty().not()) etPage.text.toString() else EMPTY_STRING
                )
                viewModel.searchResult.observe(viewLifecycleOwner) {
                    if(it.Response.isNotEmpty()){
                        searchList = it.Search as MutableList<Search>
                        initRecycler()
                    } else
                        Toast.makeText(requireContext(), NOT_FOUND_DIALOG, Toast.LENGTH_SHORT).show()
                }
                removeSearchParams()
                searchDialogLayout.isVisible = false
                view?.let { it1 -> requireContext().hideKeyboard(it1) }
            }
        }

    }

    private fun removeSearchParams() {
        etSearchTitle.setText(EMPTY_STRING)
        etPage.setText(EMPTY_STRING)
    }

    private fun initRecycler() {

        moviesAdapter = MoviesAdapter(object : MoviesAdapter.ItemListener {
            override fun onClick(id: String) {
                val action = SearchFragmentDirections.actionSearchFragmentToItemFragment()
                findNavController().navigate(action)
                imdbId = id
            }
        })

        rvMovies.adapter = moviesAdapter
        val linearLayoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        rvMovies.layoutManager = linearLayoutManager
        rvMovies.setHasFixedSize(true)
        moviesAdapter.setItems(searchList)
        tvDialog.isVisible = searchList.isEmpty()
    }
}