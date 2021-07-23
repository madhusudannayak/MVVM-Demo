package com.example.mvvmusingjetpack.fragments.dashboard

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmusingjetpack.Communicator
import com.example.mvvmusingjetpack.DiaryRVAdapter
import com.example.mvvmusingjetpack.IDiaryRVAdapter
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.databinding.FragmentDashboardBinding
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.db.DiaryRepository
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel
import com.littlemango.stacklayoutmanager.StackLayoutManager
import java.util.ArrayList


class DashboardFragment : Fragment() , IDiaryRVAdapter{
 //   class DashboardFragment : Fragment(), IDiaryRVAdapter {
    val allNote = ArrayList<DiaryData>()

    lateinit var viewModel: DiaryViewModel
    lateinit var numberofpage: TextView
    lateinit var Page: String
    lateinit var args : Bundle

    private val dashBoardViewModel: DashBoardViewModel by lazy { ViewModelProvider(this).get(DashBoardViewModel::class.java) }

//    override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View? {
//        val binding = DataBindingUtil.setContentView<FragmentDashboardBinding>(context as Activity, R.layout.fragment_dashboard)
//        binding.dashboardviewModel = dashBoardViewModel
//        binding.lifecycleOwner = this
//        return binding.root
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDashboardBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_dashboard, container, false)
           binding.dashboardviewModel = dashBoardViewModel
           binding.lifecycleOwner = this

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
           viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(DiaryViewModel::class.java)
                viewModel.allNotes.observe(viewLifecycleOwner, { list ->
            list?.let {
                adapter.updateList(it)
                allNote.addAll(it)
//                val currentNote = allNote[0]
//                          Log.d("sadasdasdsa", currentNote.text.toString())
            }
//                    viewModel.setMsgCommunicator("Hi Value Updated")

                    manager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
                        override fun onItemChanged(position: Int) {
                            numberofPage(position+1,Page.toInt())
                            args.putInt("position", position)
                            args.putInt("TotalPages", Page.toInt())
                            args.putString("Text",allNote[position].text)

 //                           Toast.makeText(context,position.toString(),Toast.LENGTH_SHORT).show();
                        }
              })
       })



         onActionPerform()
        return binding.root
    }

    private fun onActionPerform() {
        activity?.let {
            dashBoardViewModel.OpenAddFragment.observe(it,{
                openAddFragment()

        })
        }


    }

    fun openAddFragment(){
//        toast("done")
//        getNavController()
      findNavController().navigate(R.id.action_dashboardFragment_to_addFragment)

//        Toast.makeText(requireContext(),"Done",Toast.LENGTH_SHORT).show()

    }
    fun Fragment.getNavController(){
        NavHostFragment.findNavController(this).navigate(R.id.action_dashboardFragment_to_addFragment)


    }
    fun Fragment.toast(a:String){
        Toast.makeText(this.requireContext(),a,Toast.LENGTH_SHORT).show()

    }

    override fun onItemClicked(note: DiaryData, size: String) {


        findNavController().navigate(R.id.action_dashboardFragment_to_viewFragment,args)



        // communicator.passDataCom()
        Log.d("aaaaaaaaaaaa", "AAAAAAAAAAAAAAA")







      // numberofpage.text = "Page - $size of $size"
      //  Toast.makeText(context, size.toString()+"5", Toast.LENGTH_SHORT).show();

    }

    override fun size(size: String) {
      //  Toast.makeText(context, size.toString()+"5", Toast.LENGTH_SHORT).show();
        numberofPage(1,size.toInt())
        Page = size

        //  numberofpage.text = "Page - $size of $size"
    }

    fun numberofPage(CurrentPage:Int,TotalPage:Int)
    {
        numberofpage.text = "Page - $CurrentPage of $TotalPage"
    }



}

//        recyclerView.layoutManager = LinearLayoutManager(context)
//        val adapter = DiaryRVAdapter(context, this)
//        recyclerView.adapter = adapter
//
//        val manager = StackLayoutManager(StackLayoutManager.ScrollOrientation.RIGHT_TO_LEFT)
//        manager.setPagerMode(true)
//        manager.setPagerFlingVelocity(3000)
//        recyclerView.layoutManager = manager
//
//
//        viewModel = ViewModelProvider(
//                this,
//                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
//        ).get(DiaryViewModel::class.java)
//
//        viewModel.allNotes.observe(viewLifecycleOwner, { list ->
//            list?.let {
//                adapter.updateList(it)
////                val currentNote = allNote[0]
////                          Log.d("sadasdasdsa", currentNote.text.toString())
//            }
//        })

//
//        val imageButton = view.findViewById<ImageView>(R.id.imageButton)
//
//        imageButton.setOnClickListener {
//            findNavController().navigate(R.id.action_dashboardFragment_to_addFragment)
//        }
//
//        return view
//    }
//
////    override fun onItemClicked(note: DiaryData) {
////
////    }
//
//    override fun onItemClicked(note: DiaryData, size: String) {
//        findNavController().navigate(R.id.action_dashboardFragment_to_viewFragment)
//        Log.d("aaaaaaaaaaaa", "AAAAAAAAAAAAAAA")
//      //  Toast.makeText(context,size.toString(),Toast.LENGTH_SHORT).show();
//    }
////
//    override fun size(size: String) {
//        //Toast.makeText(context, size.toString()+"5", Toast.LENGTH_SHORT).show();
//        Page = size
//        numberofPage(1,size.toInt())
//    }

//
//
//}