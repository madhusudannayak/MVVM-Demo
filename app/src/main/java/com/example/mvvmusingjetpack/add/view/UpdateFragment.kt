package com.example.mvvmusingjetpack.add.view

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.add.adapter.ColorSpinnerAdapter
import com.example.mvvmusingjetpack.databinding.FragmentUpdateBinding
import com.example.mvvmusingjetpack.db.Color
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.add.model.Colors
import com.example.mvvmusingjetpack.add.viewmodel.UpdateViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateFragment : Fragment() {
    private val updateViewModel: UpdateViewModel by viewModel()

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
        spinner = binding.color
        bgCard = binding.bgCard
        note = binding.note

        val Note = args?.getString("updateNote")
        val Color = args?.getString("updateColor")
        note.setText(Note.toString())
        val updateText = args?.getString("UpdateText")
        fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

        if (updateText != null) {
            note.text = updateText.toEditable()
        }
        val color = resources.getStringArray(R.array.color)
        val adapter = ColorSpinnerAdapter(requireContext(), Colors.list!!)
        spinner.adapter = adapter
        spinner.setSelection(getIndex(Color.toString()))
        spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
            ) {
                setBackgroundColor(color[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        onActionPerform()
        return binding.root
    }


    private fun onActionPerform() {
        activity?.let {
            updateViewModel.backToViewFragment.observe(it, {
                val args = arguments
                val index = args?.getLong("position")
                Toast.makeText(context, "Update Done", Toast.LENGTH_SHORT).show()
                if (index != null) {
                    updateDataToDb(index)
                }
                findNavController().navigate(R.id.action_updateFragment_to_dashboardFragment)
            })
        }
    }

    private fun setBackgroundColor(s: String) {
        when (s) {
            "WHITE" -> {
                bgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"))
            }
            "BLUE" -> {
                bgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#87CDFF"))

            }
            "PINK" -> {
                bgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#F6CEE5"))

            }
            "YELLOW" -> {
                bgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#EAD3AC"))

            }
            "GREEN" -> {
                bgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#AED186"))

            }
            "GRAY" -> {
                bgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#CBDFF1"))

            }
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
            "PINK" -> {
                Color.PINK
            }
            "YELLOW" -> {
                Color.YELLOW
            }
            "GREEN" -> {
                Color.GREEN
            }
            "GRAY" -> {
                Color.GRAY
            }
            else -> Color.WHITE
        }
    }

    private fun updateDataToDb(index: Long) {
        val mNote = note.text.toString()
        val mColor: String = when (spinner.selectedItemId.toInt()) {
            0 -> {
                "WHITE"
            }
            1 -> {
                "BLUE"
            }
            2 -> {
                "PINK"
            }
            3 -> {
                "YELLOW"
            }
            4 -> {
                "GREEN"
            }
            5 -> {
                "GRAY"
            }
            else -> {
                "WHITE"
            }
        }
        if (mNote.isNotEmpty()) {
            updateViewModel.updateData(DiaryData(index, mNote, parseColor(mColor), 0))
        }

    }

    private fun getIndex(colorName: String): Int {
        var index = 0
        when (colorName) {
            "WHITE" -> {
                index = 0
            }
            "BLUE" -> {
                index = 1
            }
            "PINK" -> {
                index = 2
            }
            "YELLOW" -> {
                index = 3
            }
            "GREEN" -> {
                index = 4
            }
            "GRAY" -> {
                index = 5
            }
            else -> {
                index = 0
            }
        }
        return index
    }
}