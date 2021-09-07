package com.example.mvvmusingjetpack.auth.view

import android.animation.Animator
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
import com.example.mvvmusingjetpack.auth.viewmodel.SignupViewModel
import com.example.mvvmusingjetpack.databinding.FragmentSignupBinding
import com.example.mvvmusingjetpack.dashboard.view.HomeActivity
import com.example.mvvmusingjetpack.databinding.FragmentLoginBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignupFragment : Fragment() {

    private val signUpViewModel: SignupViewModel by viewModel()


    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var signUp: FloatingActionButton
    lateinit var binding: FragmentSignupBinding


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_signup, container, false
        )
        binding.signupviewModel = signUpViewModel
        binding.lifecycleOwner = this

        signUp = binding.SignUp
        email = binding.emailId
        password = binding.password

        onActionPerform()

        binding.lottie.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                binding.SignUp.visibility = View.GONE
            }

            override fun onAnimationEnd(animation: Animator?) {
                binding.lottie.visibility = View.GONE
                binding.SignUp.visibility = View.VISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }

        })

        return binding.root
    }

    private fun onActionPerform() {

        signUpViewModel.signUp.observe(requireActivity(), {
            signUp()
        })
        signUpViewModel.openLoginFragment.observe(requireActivity(), {
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
                            successAnim()
                            Toast.makeText(
                                    this.requireContext(),
                                    "You are Registered Sucessfully",
                                    Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(context, HomeActivity::class.java))
                        }
                    }.addOnFailureListener {
                        errorAnim()
                        Toast.makeText(this.requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()

                    }
        }

    }

    fun successAnim() {
        binding.lottie.setAnimation("success.json")
        binding.lottie.visibility = View.VISIBLE
        binding.lottie.playAnimation()
    }

    fun errorAnim() {
        binding.lottie.setAnimation("error.json")
        binding.lottie.visibility = View.VISIBLE
        binding.lottie.playAnimation()
    }

}