package com.example.mvvmusingjetpack.view.fragments.dashboard

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.adapter.DiaryRVAdapter
import com.example.mvvmusingjetpack.adapter.IDiaryRVAdapter
import com.example.mvvmusingjetpack.databinding.FragmentDashboardBinding
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.coroutines.*
import java.util.ArrayList


class DashboardFragment : Fragment(), IDiaryRVAdapter {
    val allNote = ArrayList<DiaryData>()
    lateinit var viewModel: DiaryViewModel
    lateinit var numberofpage: TextView
    lateinit var Page: String
    lateinit var args: Bundle
    lateinit var db: FirebaseFirestore

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
        db = FirebaseFirestore.getInstance()

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
                allNote.addAll(it)
                adapter.updateList(it)
            }
            manager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
                override fun onItemChanged(position: Int) {
                    numberofPage(position + 1, Page.toInt())
                    args.putInt("position", position)

                }
            })
        })
        onActionPerform()

        GlobalScope.launch {

            insertdatatoFirebase()

        }
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
        //  Toast.makeText(context, size.toString()+"5", Toast.LENGTH_SHORT).show();
        numberofPage(1, size.toInt())
        Page = size
        args.putInt("TotalPages", Page.toInt())


    }

    fun numberofPage(CurrentPage: Int, TotalPage: Int) {
        numberofpage.text = "Page - $CurrentPage of $TotalPage"
    }

    suspend fun insertdatatoFirebase() {
        withContext(Dispatchers.IO) {



 //           viewModel.getAllNote().

            viewModel.getUnSyncData(false)

//

                Log.d("callcorouting125355", "callllllllllll")
//                Log.d("callcorouting125355", allNote[0].text)
                for (i in 0 until allNote.size) {
                    val isSync = allNote[i].isSync
                    if (!isSync) {
                        val note = allNote[i].text
                        val color = allNote[i].color
                        val id = allNote[i].id
                        val user: MutableMap<String, Any> = HashMap()
                        user["note"] = note
                        user["color"] = color
                        user["id"] = id
                        db.collection(FirebaseAuth.getInstance().uid.toString())
                            .add(user)
                        viewModel.updateData(DiaryData(id, note, color, false))
                        Log.d("callcorouting1issync", isSync.toString())
                    }
//                val isSync = allNote[i].isSync
                }


        }

    }


}

