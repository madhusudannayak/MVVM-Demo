package com.example.mvvmusingjetpack

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.view.HomeActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var login: FloatingActionButton

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val signup = view.findViewById<TextView>(R.id.OpenSignUpPage)

        email = view.findViewById(R.id.email_Id)
        password = view.findViewById(R.id.password)
        login = view.findViewById(R.id.OpenLoginPage)

        login.setOnClickListener {
            login()
        }

        signup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        return view
    }

    fun login() {
        if (email.text.isNotEmpty() && password.text.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this.requireContext(), "You are Logged in Sucessfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(context, HomeActivity::class.java))
                        }
                    }

        }
    }


}