package com.example.mvvmusingjetpack.fragments.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmusingjetpack.DiaryRVAdapter
import com.example.mvvmusingjetpack.IDiaryRVAdapter
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel
import com.littlemango.stacklayoutmanager.StackLayoutManager
import java.util.ArrayList


class DashboardFragment : Fragment(), IDiaryRVAdapter {
 //   class DashboardFragment : Fragment(), IDiaryRVAdapter {
    val allNote = ArrayList<DiaryData>()


    lateinit var viewModel: DiaryViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var numberofpage: TextView
    lateinit var Page: String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        numberofpage = view.findViewById(R.id.numberofpage)

        recyclerView= view.findViewById(R.id.Recylerview1)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = DiaryRVAdapter(context, this)
        recyclerView.adapter = adapter

        val manager = StackLayoutManager(StackLayoutManager.ScrollOrientation.RIGHT_TO_LEFT)
        manager.setPagerMode(true)
        manager.setPagerFlingVelocity(3000)
        recyclerView.layoutManager = manager


        viewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(DiaryViewModel::class.java)

        viewModel.allNotes.observe(viewLifecycleOwner, { list ->
            list?.let {
                adapter.updateList(it)
//                val currentNote = allNote[0]
//                          Log.d("sadasdasdsa", currentNote.text.toString())
            }
        })
        manager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
            override fun onItemChanged(position: Int) {
               numberofPage(position+1,Page.toInt())
            //    Toast.makeText(context,position.toString(),Toast.LENGTH_SHORT).show();
            }
        })

        val imageButton = view.findViewById<ImageButton>(R.id.imageButton);

        imageButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_addFragment)
        }

        return view;
    }

//    override fun onItemClicked(note: DiaryData) {
//
//    }

    override fun onItemClicked(note: DiaryData, size: String) {
        findNavController().navigate(R.id.action_dashboardFragment_to_viewFragment)
        Log.d("aaaaaaaaaaaa", "AAAAAAAAAAAAAAA")
      //  Toast.makeText(context,size.toString(),Toast.LENGTH_SHORT).show();
    }
//
    override fun size(size: String) {
        //Toast.makeText(context, size.toString()+"5", Toast.LENGTH_SHORT).show();
        Page = size
        numberofPage(1,size.toInt())
    }
    fun numberofPage(CurrentPage:Int,TotalPage:Int)
    {
        numberofpage.text = "Page - $CurrentPage of $TotalPage"
    }


}