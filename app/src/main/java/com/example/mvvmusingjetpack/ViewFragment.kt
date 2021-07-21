

package com.example.mvvmusingjetpack

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel
import java.util.ArrayList

class ViewFragment : Fragment() {
    lateinit var viewModel: DiaryViewModel
    val allNote = ArrayList<DiaryData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view, container, false)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(DiaryViewModel::class.java)

//        viewModel.getNotesByID("1").observe(viewLifecycleOwner,{
//            list ->
//           list?.let {
//            val current = allNote[1]
//              Log.d("kkkkkkkkkkkkk", current.text+"saedas")
//
//
//               Log.d("kkkkkkkkkkkkk", it.toString())
//        }
//        })


//
//        viewModel.NotesByID.observe(viewLifecycleOwner, { list ->
//            list?.let {
//               // adapter.updateList(it)
//                Log.d("sadasdasdsa", it.toString())
//            }
//        })
        return view;
    }



}