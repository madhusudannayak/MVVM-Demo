package com.example.mvvmusingjetpack.auth.signup

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


class SignupFragment : Fragment() {

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var signUp: FloatingActionButton


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        val openLoginPage = view.findViewById<TextView>(R.id.OpenLoginPage)
        signUp = view.findViewById(R.id.SignUp)
        email = view.findViewById(R.id.email_Id)
        password = view.findViewById(R.id.password)



        openLoginPage.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)

        }
        signUp.setOnClickListener {
            signUp()
        }

        return view
    }

    private fun signUp() {
        var isPasswordValid = true
        var isEmailValid = true
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
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this.requireContext(), "You are Registered Sucessfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(context, HomeActivity::class.java))
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this.requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()

             }
        }

    }

}