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
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.db.DiaryViewModel


class addFragment : Fragment() {

    lateinit var mDiaryViewModel : DiaryViewModel
    var dataList = emptyList<DiaryData>()
    lateinit var note: EditText;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mDiaryViewModel = ViewModelProvider(this).get(DiaryViewModel::class.java)
        val imageButton = view.findViewById<ImageButton>(R.id.imageButton2);
        note = view.findViewById(R.id.note)

        imageButton.setOnClickListener {
         //   Log.d("sadasdasdsa", dataList.size.toString())
            insertDataToDb()
           findNavController().navigate(R.id.action_addFragment_to_dashboardFragment)
        }

        return view
    }

    private fun insertDataToDb() {
        val mNote = note.text.toString()

        val validation = DataVerify(mNote)
        if (validation){
            val newData= DiaryData(
                0,
                mNote
            )
            mDiaryViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Successfully Added", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(),"Sorry", Toast.LENGTH_SHORT).show()

        }


    }
    private fun DataVerify(note : String):Boolean{
        return !TextUtils.isEmpty(note)
    }


}