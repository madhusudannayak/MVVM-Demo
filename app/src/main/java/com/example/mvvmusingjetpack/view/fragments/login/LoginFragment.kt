package com.example.mvvmusingjetpack.view.fragments.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R
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
        val signUp = view.findViewById<TextView>(R.id.OpenSignUpPage)

        email = view.findViewById(R.id.email_Id)
        password = view.findViewById(R.id.password)
        login = view.findViewById(R.id.OpenLoginPage)


        login.setOnClickListener {
            login()
        }

        signUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        return view
    }

    private fun login() {
        var isPasswordValid: Boolean = true
        var isEmailValid: Boolean = true
        if (password.text.isEmpty()) {
            password.error = "please enter your password"
            isPasswordValid = false

        } else if (password.text.length < 8) {
            password.error = "password minimum contain 8 character"
            isPasswordValid = false
        }
        if (email.text.isEmpty()) {
            email.error = "please enter your email id"
            isEmailValid = false

        } else if (!(Patterns.EMAIL_ADDRESS.matcher(email.text).matches())) {
            email.error = "Invalid email id"
            isEmailValid = false
        }
        if (isPasswordValid && isEmailValid) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this.requireContext(), "You are Logged in Sucessfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(context, HomeActivity::class.java))
                        }

                    }.addOnFailureListener {
                        Toast.makeText(this.requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
        }
    }


}