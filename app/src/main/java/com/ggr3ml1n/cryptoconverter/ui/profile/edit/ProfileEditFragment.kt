package com.ggr3ml1n.cryptoconverter.ui.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ggr3ml1n.cryptoconverter.R
import com.ggr3ml1n.cryptoconverter.databinding.FragmentProfileEditBinding
import com.ggr3ml1n.cryptoconverter.ui.util.DelayedOnQueryTextListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileEditFragment : Fragment(R.layout.fragment_profile_edit) {

    private val viewModel: ProfileEditViewModel by viewModels()

    private var _binding: FragmentProfileEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            val profile = viewModel.uiState.value
            with(binding) {
                tvFirstName.setText(profile.firstName)
                tvLastName.setText(profile.lastName)
                tvEmail.setText(profile.email)
                tvPassword.setText(profile.password)
            }
        }

        with(binding) {
            tvFirstName.addTextChangedListener(object : DelayedOnQueryTextListener() {
                override fun onDelayerQueryTextChange(newText: String) {
                    viewModel.updateFirstName(newText)
                }
            })

            tvLastName.addTextChangedListener(object : DelayedOnQueryTextListener() {
                override fun onDelayerQueryTextChange(newText: String) {
                    viewModel.updateLastName(newText)
                }
            })

            tvEmail.addTextChangedListener(object : DelayedOnQueryTextListener() {
                override fun onDelayerQueryTextChange(newText: String) {
                    viewModel.updateEmail(newText)
                }
            })

            tvPassword.addTextChangedListener(object : DelayedOnQueryTextListener() {
                override fun onDelayerQueryTextChange(newText: String) {
                    viewModel.updatePassword(newText)
                }
            })

            btnSave.setOnClickListener {
                viewModel.saveProfile()
                findNavController().navigate(
                    ProfileEditFragmentDirections.actionNavigationEditProfileToNavigationProfile()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}