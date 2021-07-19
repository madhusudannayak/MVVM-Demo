package com.example.mvvmusingjetpack.fragments.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R

class DashboardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val imageButton = view.findViewById<ImageButton>(R.id.imageButton);

        imageButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_addFragment)
        }

        return view;
    }


}