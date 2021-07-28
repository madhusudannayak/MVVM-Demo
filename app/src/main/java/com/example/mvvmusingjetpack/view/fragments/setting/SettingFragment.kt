package com.example.mvvmusingjetpack.view.fragments.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.mvvmusingjetpack.R
import com.google.firebase.auth.FirebaseAuth


class SettingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        val user = FirebaseAuth.getInstance().currentUser
        val logout = view.findViewById<Button>(R.id.Logout)
        val email = view.findViewById<TextView>(R.id.email)

        email.text = FirebaseAuth.getInstance().app.name

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
        }





        return view;
    }


}



