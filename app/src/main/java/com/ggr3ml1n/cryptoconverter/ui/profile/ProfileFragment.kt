package com.ggr3ml1n.cryptoconverter.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ggr3ml1n.cryptoconverter.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentProfile.onEach {
            when (it) {
                is ProfileViewModel.ProfileUiState.Success -> {
                    with(binding) {
                        tvFullName.text = it.profile.fullName
                        tvEmail.text = it.profile.email
                    }
                }

                is ProfileViewModel.ProfileUiState.Error -> {
                    showErrorDialog()
                }

                else -> Unit
            }
        }.launchIn(lifecycleScope)

        lifecycleScope.launch {
            viewModel.loadProfile(1)
            delay(10000)
            viewModel.loadProfile(2)
        }
    }

    private fun showErrorDialog() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}