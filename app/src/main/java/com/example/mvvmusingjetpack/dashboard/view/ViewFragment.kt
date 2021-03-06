package com.example.mvvmusingjetpack.dashboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.databinding.FragmentViewBinding
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.dashboard.viewmodel.ViewViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ViewFragment : Fragment() {
    private val viewViewModel: ViewViewModel by viewModel()
    val Note = ArrayList<DiaryData>()
    lateinit var id: String
    lateinit var viewText: TextView
    lateinit var BgCard: CardView
    lateinit var Update: Bundle
    lateinit var Next: FloatingActionButton
    var UpdatePosition: Long = 0
    lateinit var UpdateNote: String
    lateinit var UpdateColor: String


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding: FragmentViewBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_view, container, false)
        binding.viewviewModel = viewViewModel
        binding.lifecycleOwner = this

        val args = arguments
        Next = binding.next
        BgCard = binding.bgCard
        viewText = binding.Viewtext
        val index = args?.getInt("position")
        val totalPages = args?.getInt("TotalPages")
        viewViewModel.currentID = index!!
        viewViewModel.totalPage = totalPages!!

        viewViewModel.allNotes.observe(viewLifecycleOwner, { list ->
            list?.let {
                val diaryId = args.getInt("position")
                UpdatePosition = it[diaryId].id
                UpdateNote = it[diaryId].text
                UpdateColor = it[diaryId].color.toString()
                viewText.text = it[diaryId].text
                setBackgroundColor(it[diaryId].color.toString())
                Note.addAll(it)
            }
        })

        onActionPerform()
        return binding.root
    }

    private fun onActionPerform() {
        var currentId = viewViewModel.currentID
        viewViewModel.nextItem.observe(requireActivity(), {
            if (it) {
                currentId++
                nextNote(currentId)
            } else {
                Toast.makeText(context, "Sorry No Data Found", Toast.LENGTH_SHORT).show()
            }

        })

        viewViewModel.editNote.observe(requireActivity(), {
            editNote()
        })
        viewViewModel.backToDashBoardFragment.observe(requireActivity(), {
            findNavController().navigate(R.id.action_viewFragment_to_dashboardFragment)

        })
        viewViewModel.openSettingFragment.observe(requireActivity(), {
            findNavController().navigate(R.id.action_viewFragment_to_settingFragment)

        })
        viewViewModel.openSearchFragment.observe(requireActivity(), {
            findNavController().navigate(R.id.action_viewFragment_to_searchFragment)

        })

    }

    private fun nextNote(Position: Int) {
        viewText.text = Note[Position].text
        UpdatePosition = Note[Position].id
        UpdateNote = Note[Position].text
        setBackgroundColor(Note[Position].color.toString())
    }

    private fun setBackgroundColor(s: String) {
        when (s) {
            "WHITE" -> {
                BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"))
            }
            "BLUE" -> {
                BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#87CDFF"))

            }
            "PINK" -> {
                BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#F6CEE5"))

            }
            "YELLOW" -> {
                BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#EAD3AC"))

            }
            "GREEN" -> {
                BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#b0dea0"))

            }
            "GRAY" -> {
                BgCard.setCardBackgroundColor(android.graphics.Color.parseColor("#CBDFF1"))

            }
        }

    }

    private fun editNote() {
        Update = Bundle()
        Update.putLong("position", UpdatePosition)
        Update.putString("updateNote", UpdateNote)
        Update.putString("updateColor", UpdateColor)
        findNavController().navigate(R.id.action_viewFragment_to_updateFragment, Update)
    }


}


