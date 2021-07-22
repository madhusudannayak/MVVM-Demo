

package com.example.mvvmusingjetpack.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class ViewFragment : Fragment() {
    lateinit var viewModel: DiaryViewModel
    val Note = ArrayList<DiaryData>()
    lateinit var viewText : TextView
    lateinit var BgCard : CardView
    lateinit var Next : FloatingActionButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view, container, false)

        viewText =view.findViewById<TextView>(R.id.Viewtext);
        BgCard =view.findViewById<CardView>(R.id.bgCard);
        Next = view.findViewById(R.id.next)

        fun GetValue(ID:Int){
            viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(DiaryViewModel::class.java)
            viewModel.getNotesByID(ID.toString()).observe(viewLifecycleOwner,{
                    list ->
                list?.let {
                    Note.addAll(it)

                    ViewDataByID()

                    Log.d("kkkkkkkkkkkkk1", Note[0].text)
                }
            })
        }

        Next.setOnClickListener {
            var ID = 14
            Note.clear()
            GetValue(ID)
            ID ++

        }


        return view;
    }


    fun ViewDataByID(){
        viewText.text =Note[0].text

        if (Note[0].text.equals("WHITE")){
            BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"))
        }else if(Note[0].text.equals("BLUE")){
            BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#87CDFF"))

        }
    }



}