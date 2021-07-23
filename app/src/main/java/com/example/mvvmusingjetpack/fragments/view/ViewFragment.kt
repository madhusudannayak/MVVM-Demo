

package com.example.mvvmusingjetpack.fragments.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class ViewFragment : Fragment() {
    lateinit var viewModel: DiaryViewModel
    val Note = ArrayList<DiaryData>()
    lateinit var id: String
    lateinit var viewText : TextView
    lateinit var BgCard : CardView
    lateinit var Next : FloatingActionButton
    lateinit var Edit : ImageButton
    lateinit var Update : Bundle
    var UpdatePosition : Int = 0


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view, container, false)
        val args = arguments


        viewText =view.findViewById<TextView>(R.id.Viewtext);
        BgCard =view.findViewById<CardView>(R.id.bgCard);
        Next = view.findViewById(R.id.next)
        Edit = view.findViewById(R.id.edit)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(DiaryViewModel::class.java)



        Edit.setOnClickListener {
            Update = Bundle()
            Update.putInt("position",UpdatePosition)
            Update.putString("UpdateText", viewText.text as String)

            findNavController().navigate(R.id.action_viewFragment_to_updateFragment)
        }

        viewModel.allNotes.observe(viewLifecycleOwner, { list ->
                list?.let {
                    val index = args?.getInt("position")
                    viewText.text = it[index!!].text
                    SetbackgroundColor(it[index!!].color.toString())

                    Log.d("111111111",it[index!!].text)

                    Note.addAll(it)
                    //                  ViewDataByID()
                }
            })

        val index = args?.getInt("position")
        val TotalPages = args?.getInt("TotalPages")
        val Text = args?.getString("Text")
        var position = index?.toInt()

        Next.setOnClickListener {

            if (position != null) {
                if (TotalPages != null) {
                    if (position.equals(TotalPages-1)){
                        Toast.makeText(context,"Sorry No Data Found",Toast.LENGTH_SHORT).show()
                    }else{
                        position++
                        nextNote(position)
                        UpdatePosition = position

                    }
                }

            }

        }

        return view;
    }

    private fun nextNote(Position: Int) {
        viewText.text = Note[Position].text
        SetbackgroundColor(Note[Position].color.toString())


    }

    private fun SetbackgroundColor(s: String) {
        if (s.equals("WHITE")){
            BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"))
        }else if(s.equals("BLUE")){
            BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#87CDFF"))

        }

    }










//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel.getText().observe(viewLifecycleOwner, { charSequence -> // textView.setText(charSequence);
//
//            viewText.setText(charSequence);
//         //  viewText.text = charSequence
//
//            //       Log.d("wwwwwww",charSequence as String)
//
//
//        })
//    }




    fun ViewDataByID(){
        viewText.text =Note[0].text

        if (Note[0].text.equals("WHITE")){
            BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"))
        }else if(Note[0].text.equals("BLUE")){
            BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#87CDFF"))

        }
    }



}


