package com.example.mvvmusingjetpack.view.fragments.add

import android.os.Bundle
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
import com.example.mvvmusingjetpack.adapter.ColorSpinnerAdapter
import com.example.mvvmusingjetpack.databinding.FragmentAddBinding
import com.example.mvvmusingjetpack.db.Color
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.model.Colors


class AddFragment : Fragment() {

    private val addViewModel: AddViewModel by lazy { ViewModelProvider(this).get(AddViewModel::class.java) }
    lateinit var spinner: Spinner
    lateinit var bgCard: CardView
    lateinit var edit: ImageButton
    lateinit var close: ImageButton
    lateinit var note: EditText


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentAddBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_add, container, false
        )
        binding.addviewModel = addViewModel
        binding.lifecycleOwner = this

        spinner = binding.color
        bgCard = binding.bgCard
        note = binding.note
        edit = binding.edit
        close = binding.close

        onActionPerform()
        setupCustomSpinner()
        return binding.root
    }

    private fun setupCustomSpinner() {
        val color = resources.getStringArray(R.array.color)
        val adapter = ColorSpinnerAdapter(requireContext(), Colors.list!!)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                setBackgroundColor(color[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun onActionPerform() {
        addViewModel.closeFragment.observe(requireActivity(), {
            insertDataToDb()
            findNavController().navigate(R.id.action_addFragment_to_dashboardFragment)
        })
        addViewModel.changeIcon.observe(requireActivity(), {
            if (it) {
                disableEdit()
            } else {
                enableEdit()
            }
        })

    }

    private fun disableEdit() {
        close.setImageResource(R.drawable.ic_baseline_arrow_back_24)
        edit.setImageResource(R.drawable.ic_baseline_edit_note_24)
        note.isFocusable = false
        note.isEnabled = false
        note.isCursorVisible = false

    }

    private fun enableEdit() {
        close.setImageResource(R.drawable.ic_baseline_close_24)
        edit.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
        note.isEnabled = true
        note.isFocusable = true
        note.isEnabled = true
        note.isCursorVisible = true
        note.isFocusableInTouchMode = true

    }

    private fun setBackgroundColor(color: String) {
        when (color) {
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

    private fun insertDataToDb() {

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
            addViewModel.insertNote(DiaryData(0, mNote, parseColor(mColor), 0))
        }

    }

}

