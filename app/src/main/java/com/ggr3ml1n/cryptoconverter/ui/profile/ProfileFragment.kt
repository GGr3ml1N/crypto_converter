package com.ggr3ml1n.cryptoconverter.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ggr3ml1n.cryptoconverter.R
import com.ggr3ml1n.cryptoconverter.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

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

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect {
                    updateUi(it)
                }
        }

        binding.sNightThemeMode.setOnClickListener { viewModel.changeNightThemeMode(binding.sNightThemeMode.isChecked) }

        binding.textView.setOnEditorActionListener(TextView.OnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.changeProfile(textView.text.toString().toLong())
                return@OnEditorActionListener true
            }

            return@OnEditorActionListener false
        })
    }

    private fun updateUi(state: ProfileUiState) {
        with(binding) {
            tvFullName.text = state.profile.fullName
            tvEmail.text = state.profile.email

            sNightThemeMode.isChecked = state.nightThemeMode
        }
    }

    private fun showErrorDialog() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}