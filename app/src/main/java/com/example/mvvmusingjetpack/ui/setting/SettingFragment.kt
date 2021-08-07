package com.example.mvvmusingjetpack.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.databinding.FragmentSettingBinding
import com.example.mvvmusingjetpack.ui.dashboard.viewModel.DashBoardViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingFragment : Fragment() {
  //  private val settingViewModel: SettingViewModel by lazy { ViewModelProvider(this).get(SettingViewModel::class.java) }
  private val settingViewModel: SettingViewModel by viewModel()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentSettingBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_setting, container, false)
        binding.settingviewModel = settingViewModel
        binding.lifecycleOwner = this

        val logout = binding.Logout
        val email = binding.email

        email.text = FirebaseAuth.getInstance().currentUser.toString()

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
        }


        onActionPerform()

        return binding.root;
    }

    private fun onActionPerform() {
        settingViewModel.backToDashboardFragment.observe(requireActivity(), {
            findNavController().navigate(R.id.action_settingFragment_to_dashboardFragment)

        })
    }


}

