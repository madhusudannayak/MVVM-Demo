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
import com.example.mvvmusingjetpack.databinding.FragmentAddBinding
import com.example.mvvmusingjetpack.db.Color
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel


class addFragment : Fragment() {

    private val addViewModel: AddViewModel by lazy { ViewModelProvider(this).get(AddViewModel::class.java) }
    lateinit var mDiaryViewModel: DiaryViewModel
    lateinit var spinner: Spinner
    lateinit var bgCard: CardView
    lateinit var edit: ImageButton
    lateinit var close: ImageButton
    lateinit var note: EditText

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding: FragmentAddBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_add, container, false)
        binding.addviewModel = addViewModel
        binding.lifecycleOwner = this
        mDiaryViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(DiaryViewModel::class.java)
        spinner = binding.color
        bgCard = binding.bgCard
        note = binding.note
        edit = binding.edit
        close = binding.close
        val languages = resources.getStringArray(R.array.color)


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
            addViewModel.closeFragment.observe(it, {
                insertDataToDb()
                findNavController().navigate(R.id.action_addFragment_to_dashboardFragment)
            })
        }
        activity?.let {
            addViewModel.ChangeIcon.observe(it, {
                if (it) {
                    DisableEdit()
                } else {
                    EnableEdit()
                }
            })
        }
    }

    fun DisableEdit() {
        close.setImageResource(R.drawable.ic_baseline_arrow_back_24)
        edit.setImageResource(R.drawable.ic_baseline_edit_note_24)
        note.setFocusable(false);
        note.setEnabled(false);
        note.setCursorVisible(false);

    }

    fun EnableEdit() {
        close.setImageResource(R.drawable.ic_baseline_close_24)
        edit.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
        note.isEnabled = true
        note.setFocusable(true);
        note.setEnabled(true);
        note.setCursorVisible(true);
        note.setFocusableInTouchMode(true);

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

    private fun insertDataToDb() {
        val mNote = note.text.toString()
        val color = spinner.selectedItem.toString()
        if (mNote.isNotEmpty()) {
            mDiaryViewModel.insertNote(DiaryData(0, mNote, parseColor(color)))
        }
    }
}