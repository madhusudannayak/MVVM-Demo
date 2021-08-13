package com.example.mvvmusingjetpack.auth.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.auth.viewmodel.SignupViewModel
import com.example.mvvmusingjetpack.databinding.FragmentSignupBinding
import com.example.mvvmusingjetpack.ui.HomeActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth


class SignupFragment : Fragment() {

    private val signupViewModel: SignupViewModel by lazy {
        ViewModelProvider(this).get(
            SignupViewModel::class.java
        )
    }

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var signUp: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentSignupBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_signup, container, false
        )
        binding.signupviewModel = signupViewModel
        binding.lifecycleOwner = this

        signUp = binding.SignUp
        email = binding.emailId
        password = binding.password

        onActionPerform()

        return binding.root
    }

    private fun onActionPerform() {

        signupViewModel.signUp.observe(requireActivity(), {
            signUp()
        })
        signupViewModel.openLoginFragment.observe(requireActivity(), {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        })

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
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this.requireContext(),
                            "You are Registered Sucessfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(context, HomeActivity::class.java))
                    }
                }.addOnFailureListener {
                    Toast.makeText(this.requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()

                }
        }

    }

}