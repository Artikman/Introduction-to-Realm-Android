package com.example.realm.ui.updateviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.updateviews.UpdateViewsViewModel
import com.example.realm.R
import com.example.realm.databinding.FragmentAddViewsBinding
import com.example.realm.ui.utils.hideKeyboard

class UpdateViewsFragment : Fragment() {

    private val addViewsViewModel =
        ViewModelProvider.NewInstanceFactory().create(UpdateViewsViewModel::class.java)

    private lateinit var binding: FragmentAddViewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddViewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btAddConfirm.setOnClickListener {
                hideKeyboard()
                addViewsViewModel.updateViewCount(etViewCount.text.toString().toInt())
            }

            addViewsViewModel.visitInfo.observe(viewLifecycleOwner) {
                tvViewCount.text = resources.getString(R.string.update_view_count, it)
            }
        }
    }
}