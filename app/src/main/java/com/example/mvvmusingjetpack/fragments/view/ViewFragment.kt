

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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.databinding.FragmentDashboardBinding
import com.example.mvvmusingjetpack.databinding.FragmentViewBinding
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.fragments.dashboard.DashBoardViewModel
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class ViewFragment : Fragment() {

    lateinit var viewModel: DiaryViewModel
    val Note = ArrayList<DiaryData>()
    lateinit var id: String
    lateinit var viewText : TextView
    lateinit var BgCard : CardView
    lateinit var Update : Bundle
    lateinit var Next : FloatingActionButton
    var UpdatePosition : Int = 0
    lateinit var UpdateNote : String
    lateinit var UpdateColor : String


    private val viewViewModel: ViewViewModel by lazy { ViewModelProvider(this).get(ViewViewModel::class.java) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View{
        val binding: FragmentViewBinding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_view, container, false)
        binding.viewviewModel = viewViewModel
        binding.lifecycleOwner = this
        val args = arguments
        Next = binding.next
        BgCard = binding.bgCard
        viewText = binding.Viewtext
 //       val args = arguments
 //       var index = args?.getInt("position")
 //       val TotalPages = args?.getInt("TotalPages")

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(DiaryViewModel::class.java)
        viewModel.allNotes.observe(viewLifecycleOwner, { list ->
                list?.let {
                    val index = args?.getInt("position")
                    UpdatePosition = it[index!!].id
                    UpdateNote = it[index!!].text
                    UpdateColor = it[index!!].color.toString()
                    viewText.text = it[index!!].text
                    SetbackgroundColor(it[index!!].color.toString())
                    Log.d("111111111",it[index!!].text)

                    Note.addAll(it)
                    //                  ViewDataByID()
                }
            })

//        Next.setOnClickListener {
//            var position = index?.toInt()
//            if (position != null) {
//                if (TotalPages != null) {
//                    if (position.equals(TotalPages-1)){
//                        Toast.makeText(context,"Sorry No Data Found",Toast.LENGTH_SHORT).show()
//                    }else{
//
//                        position++
//                        Log.d("totalPage",TotalPages.toString())
//                        Log.d("totalPageposition",position.toString())
//                        nextNote(position)
//                        Log.d("update11",position.toString())
//
//                        //                       UpdatePosition
////                        UpdatePosition = Note[position-1].id
//
//                    }
//                }
//            }
//
//        }

        onActionPerform()
        return binding.root
    }

    private fun onActionPerform() {
        val args = arguments
        val index = args?.getInt("position")
        val TotalPages = args?.getInt("TotalPages")
        var position = index?.toInt()

        activity?.let {
            viewViewModel.NextItem.observe(it,{
                var position = index?.toInt()
                if (position != null) {
                    if (TotalPages != null) {
                        if (position.equals(TotalPages-1)){
                            Toast.makeText(context,"Sorry No Data Found",Toast.LENGTH_SHORT).show()
                        }else{
                            position++
                            nextNote(position)

                        }
                    }
                }
            })

            viewViewModel.EditNote.observe(it,{
                EditNote()
            })
            viewViewModel.BackToDashBoardFragment.observe(it,{
                findNavController().navigate(R.id.action_viewFragment_to_dashboardFragment)

            })
        }




    }

    private fun nextNote(Position: Int) {
        Log.d("update111",Note[Position].text)

        viewText.text = Note[Position].text
        UpdatePosition = Note[Position].id
        UpdateNote = Note[Position].text
        SetbackgroundColor(Note[Position].color.toString())

    }

    private fun SetbackgroundColor(s: String) {
        if (s.equals("WHITE")){
            BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"))
        }else if(s.equals("BLUE")){
            BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#87CDFF"))

        }

    }
    fun EditNote(){
        Update = Bundle()
        Update.putInt("position",UpdatePosition)
        Update.putString("updateNote",UpdateNote)
        Update.putString("updateColor",UpdateColor)
        findNavController().navigate(R.id.action_viewFragment_to_updateFragment,Update)
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


