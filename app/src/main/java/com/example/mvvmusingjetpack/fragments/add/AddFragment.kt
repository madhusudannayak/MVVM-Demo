package com.example.mvvmusingjetpack.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel
import java.util.ArrayList


class addFragment : Fragment() {

    lateinit var mDiaryViewModel : DiaryViewModel
    val allNote = ArrayList<DiaryData>()
    lateinit var note: EditText;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mDiaryViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(DiaryViewModel::class.java)
        val imageButton = view.findViewById<ImageButton>(R.id.imageButton2);
        note = view.findViewById(R.id.note)

        imageButton.setOnClickListener {
            mDiaryViewModel.allNotes.observe(viewLifecycleOwner
                ,  {list ->
                    list?.let {
                        Log.d("sadasdasdsa", it.toString())
                    }

                })
            insertDataToDb()
           findNavController().navigate(R.id.action_addFragment_to_dashboardFragment)
        }

        return view
    }

    private fun insertDataToDb() {
        val mNote = note.text.toString()
        if(mNote.isNotEmpty()){
            mDiaryViewModel.insertNote(DiaryData(0,mNote))
        }



//        val mNote = note.text.toString()
//
//        val validation = DataVerify(mNote)
//        if (validation){
//            val newData= DiaryData(
//                0,
//                mNote
//            )
//            mDiaryViewModel.insertNote(newData)
//            Toast.makeText(requireContext(),"Successfully Added", Toast.LENGTH_SHORT).show()
//        }else{
//            Toast.makeText(requireContext(),"Sorry", Toast.LENGTH_SHORT).show()
//
//        }


    }
    private fun DataVerify(note : String):Boolean{
        return !TextUtils.isEmpty(note)
    }


}