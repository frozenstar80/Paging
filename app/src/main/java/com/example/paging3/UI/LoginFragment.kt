package com.example.paging3.UI

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.githubrepofinder.ui.BaseFragment
import com.example.paging3.Data.Network.ApiService
import com.example.paging3.R
import com.example.paging3.ViewModelFactory
import com.example.paging3.databinding.FragmentLoginBinding
import com.example.paging3.utils.Status
import com.example.paging3.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    val sharedPref = activity?.getSharedPreferences(
    getString(R.string.preference_file_key), Context.MODE_PRIVATE)


    @Inject
    lateinit var apiService: ApiService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        setupViewModel()

        binding.btnLogin.setOnClickListener {
            login(binding.etEmail.text.toString().trim(), binding.etPassword.text.toString().trim())

        }


        return binding.root
    }



    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(apiService)
        ).get(LoginViewModel::class.java)
    }


fun login(username:String,password:String){
        viewModel.getUsers(username, password).observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                        resource.data?.let { users ->

                            if (users.successMessage == "Login Successful.") {
                                with(sharedPref?.edit()) {
                                    this?.putInt("key", users.data?.key!!)
                                    this?.apply()
                                }
                                Toast.makeText(
                                    requireActivity(),
                                    users.successMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                                Navigation.findNavController(
                                    requireActivity(),
                                    R.id.fragmentContainerView
                                ).navigate(R.id.action_repoDetailsFragment_to_detailsFragment)

                            }
                        }
                    }
                    Status.ERROR -> {

                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {

                    }
                }
            }
        }
}

}