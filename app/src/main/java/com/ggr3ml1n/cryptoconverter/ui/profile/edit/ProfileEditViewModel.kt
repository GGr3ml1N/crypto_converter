package com.ggr3ml1n.cryptoconverter.ui.profile.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ggr3ml1n.cryptoconverter.model.Profile
import com.ggr3ml1n.cryptoconverter.repository.ProfileEditRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val profileEditRepository: ProfileEditRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(Profile())
    val uiState: StateFlow<Profile> = _uiState.asStateFlow()

    private val profileParam = savedStateHandle.getStateFlow("profile", Profile())

    init {
        _uiState.value = savedStateHandle["profile"]!!

        profileParam
            .onEach { _uiState.value = it }
            .launchIn(viewModelScope)
    }

    fun updateFirstName(newFirstName: String) {
        savedStateHandle["profile"] = _uiState.value.copy(firstName = newFirstName)
    }

    fun updateLastName(newLastName: String) {
        savedStateHandle["profile"] = _uiState.value.copy(lastName = newLastName)
    }

    fun updateEmail(newEmail: String) {
        savedStateHandle["profile"] = _uiState.value.copy(email = newEmail)
    }

    fun updatePassword(newPassword: String) {
        savedStateHandle["profile"] = _uiState.value.copy(password = newPassword)
    }

    fun saveProfile() {
        viewModelScope.launch {
            profileEditRepository.save(_uiState.value)
        }
    }
}