package com.example.realm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.example.realm.R
import com.example.realm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by navGraphViewModels(R.id.mobile_navigation)
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            homeViewModel.text.observe(viewLifecycleOwner) {
                textHome.text = it
            }

            homeViewModel.visitInfo.observe(viewLifecycleOwner) {
                textHomeSubtitle.text = resources.getString(R.string.count, it)
            }

            btRefreshCount.setOnClickListener {
                homeViewModel.onRefreshCount()
            }
        }
    }
}