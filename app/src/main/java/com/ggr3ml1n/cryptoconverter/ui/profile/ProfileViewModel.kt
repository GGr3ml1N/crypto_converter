package com.ggr3ml1n.cryptoconverter.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ggr3ml1n.cryptoconverter.repository.ProfileRepository
import com.ggr3ml1n.cryptoconverter.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _currentProfile = userPreferencesRepository.profileIdFlow
        .transform { profileId -> emit(profileRepository.getCurrentProfile(profileId)) }

    private val _nightThemeModeFlow = userPreferencesRepository.nightThemeModeFlow

    private val _combined =
        combine(_currentProfile, _nightThemeModeFlow) { currentProfile, nightThemeMode ->
            _uiState.value.copy(
                profile = currentProfile,
                nightThemeMode = nightThemeMode,
                error = null
            )
        }

    init {
        _combined.catch { error -> _uiState.update { it.copy(error = error) } }
            .onEach { newState -> _uiState.value = newState }
            .launchIn(viewModelScope)
    }

    fun changeNightThemeMode(nightThemeMode: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.updateNightTheme(nightThemeMode)
        }
    }

    // TODO: temp test method
    fun changeProfile(id: Long) {
        viewModelScope.launch {
            userPreferencesRepository.updateUserId(id)
        }
    }
}