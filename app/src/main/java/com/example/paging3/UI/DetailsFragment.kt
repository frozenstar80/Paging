package com.example.paging3.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubrepofinder.ui.BaseFragment
import com.example.paging3.Adapter.DogsAdapter
import com.example.paging3.Adapter.LoaderStateAdapter
import com.example.paging3.databinding.FragmentDetailsBinding
import com.example.paging3.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : BaseFragment(){

    private lateinit var binding: FragmentDetailsBinding
    private val mainViewModel: MainViewModel by viewModels()
    @Inject
    lateinit var dogsAdapter: DogsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater,container,false)

        initRecyclerview()
        lifecycleScope.launchWhenStarted {
            mainViewModel.getAllDogs.collectLatest { response->
                binding.apply {
                    progressBar.isVisible=false
                    recyclerview.isVisible=true
                }
                dogsAdapter.submitData(response)
            }
        }

        return binding.root
    }

    private fun initRecyclerview() {
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(requireActivity(),2)
                adapter = dogsAdapter.withLoadStateHeaderAndFooter(
                    header = LoaderStateAdapter { dogsAdapter :: retry},
                    footer = LoaderStateAdapter{dogsAdapter :: retry}
                )
            }
        }
    }
}
