package com.example.mvvmusingjetpack.fragments.add

import android.graphics.Color.parseColor
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.db.Color
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel


class addFragment : Fragment() {

    lateinit var mDiaryViewModel : DiaryViewModel
    lateinit var spinner: Spinner
    lateinit var bgCard : CardView
   // val allNote = ArrayList<DiaryData>()
    lateinit var note: EditText;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val languages = resources.getStringArray(R.array.color)
        mDiaryViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(DiaryViewModel::class.java)
        val imageButton = view.findViewById<ImageButton>(R.id.imageButton2);
        note = view.findViewById(R.id.note)
        spinner = view.findViewById(R.id.color)
        bgCard = view.findViewById(R.id.bgCard)
        spinner.adapter = activity?.let {
            ArrayAdapter(
                    it,
                R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.color)
        )}
        spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                SetbackgroundColor(languages[position])


              //  Toast.makeText(context,languages[position]+"", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

//          spinner.onItemClickListener = object:
//          AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
//              override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//
//              }
//
//              override fun onNothingSelected(parent: AdapterView<*>?) {
//              }
//
//              override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                  Toast.makeText(requireContext(),position.toString(), Toast.LENGTH_SHORT).show()
//              }
//
//          }

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

    private fun SetbackgroundColor(s: String) {
       if (s.equals("WHITE")){
           bgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"))
       }else if(s.equals("BLUE")){
           bgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#87CDFF"))

       }

    }

    private fun insertDataToDb() {
        val mNote = note.text.toString()
        val color= spinner.selectedItem.toString()
        if(mNote.isNotEmpty()){
            mDiaryViewModel.insertNote(DiaryData(0,mNote,parseColor(color)))
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
    private fun parseColor(color: String): Color{
        return when(color){
            "WHITE" -> {Color.WHITE}
            "BLUE" -> {Color.BLUE}
            else -> Color.WHITE
        }
    }


}