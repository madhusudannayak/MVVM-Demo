package com.example.mvvmusingjetpack.fragments.update

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.databinding.FragmentUpdateBinding
import com.example.mvvmusingjetpack.db.Color
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel
import java.util.ArrayList

class UpdateFragment : Fragment() {
    val allNote = ArrayList<DiaryData>()

    private val updateViewModel: UpdateViewModel by lazy { ViewModelProvider(this).get(UpdateViewModel::class.java) }
    lateinit var viewModel: DiaryViewModel
    lateinit var spinner: Spinner
    lateinit var bgCard: CardView
    lateinit var edit: ImageButton
    lateinit var close: ImageButton
    lateinit var note: EditText
    var a:String =""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val binding: FragmentUpdateBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_update, container, false)
        val args = arguments

        binding.updateviewModel = updateViewModel
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(DiaryViewModel::class.java)
        spinner = binding.color
        bgCard = binding.bgCard
        note = binding.note
        edit = binding.edit
        close = binding.close
   val index = args?.getInt("position")
   val Note = args?.getString("updateNote")


        viewModel.allNotes.observe(viewLifecycleOwner, { list ->
            list?.let {
                allNote.addAll(it)
//                val index = args?.getInt("position")
              Log.d("111111111a",index!!.toString())

              Log.d("111111111",Note.toString())
                note.setText(Note.toString())


//                SetbackgroundColor(it[index!!].color.toString())
//                Log.d("111111111",it[index!!].text)

                //                  ViewDataByID()
            }
        })

//        mDiaryViewModel.allNotes.observe(viewLifecycleOwner, { list ->
//            list?.let {
//              //  viewText.text = it[index!!].text
// //               val index = args?.getInt("position")
////               SetbackgroundColor(it[index!!].color.toString())
//               Log.d("111111111",index!!.toString())
//               Log.d("111111111",it[index!!].text.toString())
//
//                //                  ViewDataByID()
//            }
//        })







        val languages = resources.getStringArray(R.array.color)
        val updateText = args?.getString("UpdateText")

        fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

        close.setOnClickListener {
//            Toast.makeText(context,"Done",Toast.LENGTH_SHORT).show()
//            if (index != null) {
//                UpdateDataToDb(index)
//            }
        }


        if (updateText != null) {
            note.text = updateText.toEditable()
        }

        spinner.adapter = activity?.let {
            ArrayAdapter(it,
                    R.layout.support_simple_spinner_dropdown_item,
                    resources.getStringArray(R.array.color)
            )
        }
        spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                SetbackgroundColor(languages[position])

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }


     onActionPerform()



        return binding.root
    }

    private fun onActionPerform() {
        activity?.let {
            updateViewModel.BackToViewFragment.observe(it,{
                val args = arguments
                val index = args?.getInt("position")
                Log.d("111111111111",index.toString())
                Log.d("111111111111a",a.toString())
                Toast.makeText(context,"Update Done",Toast.LENGTH_SHORT).show()
                if (index != null) {
                    UpdateDataToDb(index)
                }
                findNavController().navigate(R.id.action_updateFragment_to_dashboardFragment)
            })
        }


    }
    fun DisableEdit(){
        close.setImageResource(R.drawable.ic_baseline_arrow_back_24)
        edit.setImageResource(R.drawable.ic_baseline_edit_note_24)
        note.setFocusable(false);
        note.setEnabled(false);
        note.setCursorVisible(false);

    }
    fun EnableEdit(){
        close.setImageResource(R.drawable.ic_baseline_close_24)
        edit.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
        note.isEnabled =true
        note.setFocusable(true);
        note.setEnabled(true);
        note.setCursorVisible(true);
        note.setFocusableInTouchMode(true);


    }

    private fun SetbackgroundColor(s: String) {
        if (s.equals("WHITE")){
            bgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"))
        }else if(s.equals("BLUE")){
            bgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#87CDFF"))

        }

    }


    private fun parseColor(color: String): Color {
        return when(color){
            "WHITE" -> {
                Color.WHITE}
            "BLUE" -> {
                Color.BLUE}
            else -> Color.WHITE
        }
    }
    private fun UpdateDataToDb(index: Int) {
       // val index = args?.getInt("position")

        val mNote = note.text.toString()
        val color= spinner.selectedItem.toString()
      //  if(mNote.isNotEmpty()){
        Log.d("update1",index.toString())
        Log.d("update2",color)
        viewModel.updateData(DiaryData(index,mNote, parseColor(color)))


       // }

    }

//        val imageButton = view.findViewById<ImageButton>(R.id.imageButton2);
//        note = view.findViewById(R.id.note)
//        spinner = view.findViewById(R.id.color)
//        bgCard = view.findViewById(R.id.bgCard)
//
//
////          spinner.onItemClickListener = object:
////          AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
////              override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
////
////              }
////
////              override fun onNothingSelected(parent: AdapterView<*>?) {
////              }
////
////              override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
////                  Toast.makeText(requireContext(),position.toString(), Toast.LENGTH_SHORT).show()
////              }
////
////          }
//
//        imageButton.setOnClickListener {
//            mDiaryViewModel.allNotes.observe(viewLifecycleOwner
//                ,  {list ->
//                    list?.let {
//                        Log.d("sadasdasdsa", it.toString())
//                    }
//
//                })
//            insertDataToDb()
//           findNavController().navigate(R.id.action_addFragment_to_dashboardFragment)
//        }
//
//        return view
//    }
//
//
//
//
//
////        val mNote = note.text.toString()
////
////        val validation = DataVerify(mNote)
////        if (validation){
////            val newData= DiaryData(
////                0,
////                mNote
////            )
////            mDiaryViewModel.insertNote(newData)
////            Toast.makeText(requireContext(),"Successfully Added", Toast.LENGTH_SHORT).show()
////        }else{
////            Toast.makeText(requireContext(),"Sorry", Toast.LENGTH_SHORT).show()
////
////        }
//
//
//    }
//    private fun DataVerify(note : String):Boolean{
//        return !TextUtils.isEmpty(note)
//    }



}