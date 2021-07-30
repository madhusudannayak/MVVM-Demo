package com.example.mvvmusingjetpack.view.fragments.update

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.databinding.FragmentUpdateBinding
import com.example.mvvmusingjetpack.db.Color
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UpdateFragment : Fragment() {

    private val updateViewModel: UpdateViewModel by lazy { ViewModelProvider(this).get(UpdateViewModel::class.java) }
    lateinit var viewModel: DiaryViewModel
    lateinit var spinner: Spinner
    lateinit var bgCard: CardView
    lateinit var note: EditText
    lateinit var db: FirebaseFirestore



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding: FragmentUpdateBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_update, container, false)
        val args = arguments

        binding.updateviewModel = updateViewModel
        binding.lifecycleOwner = this
        db = FirebaseFirestore.getInstance()
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(DiaryViewModel::class.java)
        spinner = binding.color
        bgCard = binding.bgCard
        note = binding.note

        val Note = args?.getString("updateNote")
        val Color = args?.getString("updateColor")
        note.setText(Note.toString())
        SetbackgroundColor(Color.toString())


        val languages = resources.getStringArray(R.array.color)
        val updateText = args?.getString("UpdateText")

        fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)


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
                spinner.setSelection(getIndex(spinner,Color.toString()))

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
            updateViewModel.BackToViewFragment.observe(it, {
                val args = arguments
                val index = args?.getLong("position")
                Toast.makeText(context, "Update Done", Toast.LENGTH_SHORT).show()
                if (index != null) {
                    UpdateDataToDb(index)
                }
                findNavController().navigate(R.id.action_updateFragment_to_dashboardFragment)
            })
        }
    }

    private fun SetbackgroundColor(s: String) {
        if (s.equals("WHITE")) {
            bgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"))
        } else if (s.equals("BLUE")) {
            bgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#87CDFF"))

        }
    }

    private fun parseColor(color: String): Color {
        return when (color) {
            "WHITE" -> {
                Color.WHITE
            }
            "BLUE" -> {
                Color.BLUE
            }
            else -> Color.WHITE
        }
    }

    private fun UpdateDataToDb(index: Long) {
        val mNote = note.text.toString()
        val color = spinner.selectedItem.toString()

        if (mNote.isNotEmpty()) {
//            db.collection(FirebaseAuth.getInstance().uid.toString())
//                    .document().update()
//            val user: MutableMap<String, Any> = HashMap()
//            user["note"] = mNote
//            user["color"] = color
//            user["id"] = index
//            db.collection(FirebaseAuth.getInstance().uid.toString()).document(it[i].id.toString())
//                    .set(user)
            viewModel.updateData(DiaryData(index, mNote, parseColor(color), 0))

        }

    }
    private fun getIndex(spinner: Spinner, myString: String): Int {
        var index = 0
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i) == myString) {
                index = i
            }
        }
        return index
    }


}