package com.example.mvvmusingjetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SignupFragment : Fragment() {

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var SignUp: FloatingActionButton

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        val OpenLoginPage = view.findViewById<TextView>(R.id.OpenLoginPage)
        SignUp = view.findViewById(R.id.SignUp)
        email = view.findViewById(R.id.email_Id)
        password = view.findViewById(R.id.password)


        OpenLoginPage.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)

        }
        SignUp.setOnClickListener {
            Signup()
        }


        return view
    }

    fun Signup() {
        if (email.text.isNotEmpty() && password.text.isNotEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(this.requireContext(), "You are Registered Sucessfully", Toast.LENGTH_SHORT).show()
                        }
                    }
        }


    }

}