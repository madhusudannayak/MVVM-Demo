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
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.auth.viewmodel.LoginViewModel
import com.example.mvvmusingjetpack.databinding.FragmentLoginBinding
import com.example.mvvmusingjetpack.dashboard.view.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

   // private val loginViewModel: LoginViewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }

    private val loginViewModel: LoginViewModel by viewModel()


    lateinit var email: EditText
    lateinit var password: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )
        binding.loginviewModel = loginViewModel
        binding.lifecycleOwner = this
        email = binding.emailId
        password = binding.password

        onActionPerform()

        return binding.root
    }

    private fun onActionPerform() {
        loginViewModel.login.observe(requireActivity(), {
            login()
        })
        loginViewModel.openSignupFragment.observe(requireActivity(), {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        })

    }

    private fun login() {
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
                .signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this.requireContext(),
                            "You are Logged in Sucessfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(context, HomeActivity::class.java))
                    }

                }.addOnFailureListener {
                    Toast.makeText(this.requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
        }
    }


}