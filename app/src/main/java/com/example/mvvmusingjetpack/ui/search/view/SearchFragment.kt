package com.example.mvvmusingjetpack.ui.search.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.ui.search.adapter.SearchRVAdapter
import com.example.mvvmusingjetpack.databinding.FragmentSearchBinding
import com.example.mvvmusingjetpack.ui.search.viewModel.SearchViewModel
import com.littlemango.stacklayoutmanager.StackLayoutManager


class SearchFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SearchRVAdapter


    private val seachViewModel: SearchViewModel by lazy { ViewModelProvider(this).get(
        SearchViewModel::class.java) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_search, container, false)

        binding.searchviewModel = seachViewModel
        binding.lifecycleOwner = this

        val search = binding.searchNote
        recyclerView = binding.Recylerview1
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = SearchRVAdapter(context)
        recyclerView.adapter = adapter
        val manager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        manager.setPagerMode(true)
        manager.setPagerFlingVelocity(3000)
        recyclerView.layoutManager = manager
        search.onActionViewExpanded()

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchNote(newText)
                return false
            }

        })
        onActionPerform()

        return binding.root
    }

    private fun onActionPerform() {
        seachViewModel.backToViewFragment.observe(requireActivity(), {
            findNavController().navigate(R.id.action_searchFragment_to_dashboardFragment)
        })


    }

    fun searchNote(value: String) {
        val searchValue = "%$value%"
        seachViewModel.searchNote(searchValue).observe(viewLifecycleOwner, {
            adapter.updateList(it)

        })
    }


}