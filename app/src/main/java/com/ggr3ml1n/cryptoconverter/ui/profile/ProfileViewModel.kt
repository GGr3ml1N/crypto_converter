package com.ggr3ml1n.cryptoconverter.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ggr3ml1n.cryptoconverter.model.Profile
import com.ggr3ml1n.cryptoconverter.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _currentProfile = MutableStateFlow<ProfileUiState>(ProfileUiState.Empty)
    val currentProfile: StateFlow<ProfileUiState> = _currentProfile

    fun loadProfile(id: Long) = profileRepository.getCurrentProfile(id)
        .onEach { profile -> _currentProfile.value = profile }
        .catch { _currentProfile.value = ProfileUiState.Error(it) }
        .launchIn(viewModelScope)

    sealed class ProfileUiState {
        data class Success(val profile: Profile) : ProfileUiState()
        data class Error(val message: Throwable) : ProfileUiState()
        object Empty : ProfileUiState()
    }
}