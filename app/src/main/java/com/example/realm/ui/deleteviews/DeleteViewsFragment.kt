package com.example.realm.ui.deleteviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.realm.R
import com.example.realm.databinding.FragmentDeleteViewsBinding
import com.example.realm.ui.utils.hideKeyboard

class DeleteViewsFragment : Fragment() {

    private val deleteViewModel =
        ViewModelProvider.NewInstanceFactory().create(DeleteViewsViewModels::class.java)
    private lateinit var binding: FragmentDeleteViewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeleteViewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btDeleteConfirm.setOnClickListener {
                hideKeyboard()
                deleteViewModel.deleteViewCount(etViewCount.text.toString().toInt())
            }

            deleteViewModel.visitInfo.observe(viewLifecycleOwner) {
                tvViewCount.text = resources.getString(R.string.update_view_count, it)
            }
        }
    }
}