package com.example.mvvmusingjetpack.view.fragments.dashboard

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.adapter.DiaryRVAdapter
import com.example.mvvmusingjetpack.adapter.IDiaryRVAdapter
import com.example.mvvmusingjetpack.databinding.FragmentDashboardBinding
import com.example.mvvmusingjetpack.db.Color
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlin.collections.set


class DashboardFragment : Fragment(), IDiaryRVAdapter {
    lateinit var viewModel: DiaryViewModel
    lateinit var numberofpage: TextView
    lateinit var Page: String
    lateinit var args: Bundle
    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var isEmpty: ImageView
    lateinit var emptylayout: LinearLayout

    private val dashBoardViewModel: DashBoardViewModel by lazy {
        ViewModelProvider(this).get(
                DashBoardViewModel::class.java
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDashboardBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_dashboard, container, false
        )
        binding.dashboardviewModel = dashBoardViewModel
        binding.lifecycleOwner = this
        isEmpty = binding.isEmpty
        emptylayout = binding.isEmptylayout
//        db = FirebaseFirestore.getInstance()

        args = Bundle()
        numberofpage = binding.numberofpage

        val recyclerView = binding.Recylerview1
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = DiaryRVAdapter(context, this)
        recyclerView.adapter = adapter
        val manager = StackLayoutManager(StackLayoutManager.ScrollOrientation.RIGHT_TO_LEFT)
        manager.setPagerMode(true)
        manager.setPagerFlingVelocity(3000)
        recyclerView.layoutManager = manager

        viewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(DiaryViewModel::class.java)

        viewModel.allNotes.observe(viewLifecycleOwner, { list ->
            list?.let {
                if (it.size.equals(0)) {
                    emptyFile()
                } else {
                    emptylayout.visibility = View.GONE
                    adapter.updateList(it)
                }

            }
            manager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
                override fun onItemChanged(position: Int) {
                    numberofPage(position + 1, Page.toInt())
                    args.putInt("position", position)

                }
            })
        })
        onActionPerform()
        exportCloudFirestoreData()
        importCloudFirestoreData()
        return binding.root
    }

    private fun onActionPerform() {
        activity?.let {
            dashBoardViewModel.OpenAddFragment.observe(it, {
                openAddFragment()

            })
        }


    }

    fun openAddFragment() {
        findNavController().navigate(R.id.action_dashboardFragment_to_addFragment)

    }

    fun Fragment.getNavController() {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_dashboardFragment_to_addFragment)


    }

    fun Fragment.toast(a: String) {
        Toast.makeText(this.requireContext(), a, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClicked(note: DiaryData, size: String) {

        findNavController().navigate(R.id.action_dashboardFragment_to_viewFragment, args)

    }

    override fun size(size: String) {
        numberofPage(1, size.toInt())
        Page = size
        args.putInt("TotalPages", Page.toInt())

    }

    override fun deleteNote(note: DiaryData) {

        view?.let {
            Snackbar.make(it, "Are you sure you want to delete the note?", Snackbar.LENGTH_SHORT)
                    .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                    .setBackgroundTint(android.graphics.Color.parseColor("#CBA3FF"))
                    .setActionTextColor(android.graphics.Color.BLACK)
                    .setAction("Delete") {
                        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                        db.collection(FirebaseAuth.getInstance().uid.toString()).document(note.id.toString()).delete()
                        viewModel.deleteNode(note)
                    }.show()
        }

    }

    fun numberofPage(CurrentPage: Int, TotalPage: Int) {
        numberofpage.text = "Page - $CurrentPage of $TotalPage"
    }

    fun exportCloudFirestoreData() {
        viewModel.getAllNotesUnsynced().observe(requireActivity(), {

            for (i in 0 until it.size) {
                val note = it[i].text
                val color = it[i].color
                val id = it[i].id

                val user: MutableMap<String, Any> = HashMap()
                user["note"] = note
                user["color"] = color
                user["id"] = id

                db.collection(FirebaseAuth.getInstance().uid.toString()).document(it[i].id.toString())
                        .set(user)
                        .addOnSuccessListener {
                            viewModel.updateData(DiaryData(id, note, color, 1))
                        }

            }
        })
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

    fun importCloudFirestoreData() {
        db.collection(FirebaseAuth.getInstance().uid.toString())
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val id = document.getLong("id")!!
                        val note = document.get("note").toString()
                        val color = document.get("color").toString()
                        viewModel.insertNote(DiaryData(id, note, parseColor(color), 1))
                    }
                }

    }

    fun emptyFile() {
        Glide.with(this).load(R.drawable.book).into(isEmpty)
        emptylayout.visibility = View.VISIBLE
    }


}

