package com.example.mvvmusingjetpack.view.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.adapter.DiaryRVAdapter
import com.example.mvvmusingjetpack.adapter.IDiaryRVAdapter
import com.example.mvvmusingjetpack.adapter.SearchRVAdapter
import com.example.mvvmusingjetpack.databinding.FragmentSearchBinding
import com.example.mvvmusingjetpack.databinding.FragmentViewBinding
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.view.fragments.view.ViewViewModel
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel
import com.littlemango.stacklayoutmanager.StackLayoutManager


class SearchFragment : Fragment(), IDiaryRVAdapter {
    lateinit var viewModel: DiaryViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SearchRVAdapter


    private val seachViewModel: SearchViewModel by lazy { ViewModelProvider(this).get(SearchViewModel::class.java) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_search, container, false)
        binding.searchviewModel = seachViewModel
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(DiaryViewModel::class.java)

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
        viewModel.searchNote(searchValue).observe(viewLifecycleOwner, { list ->
            list?.let {
                adapter.updateList(it)
            }
        })
    }

    override fun onItemClicked(note: DiaryData, size: String) {
    }

    override fun size(size: String) {
    }

    override fun deleteNote(note: DiaryData) {
    }


}