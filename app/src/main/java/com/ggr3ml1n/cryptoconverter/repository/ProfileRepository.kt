package com.ggr3ml1n.cryptoconverter.repository

import com.ggr3ml1n.cryptoconverter.temp.Database
import com.ggr3ml1n.cryptoconverter.ui.profile.ProfileViewModel.ProfileUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val database: Database
) {

    fun getCurrentProfile(id: Long) = flow {
        emit(database.getProfileById(id))
    }
        .flowOn(Dispatchers.IO)
        .map { ProfileUiState.Success(it) }
        .catch<ProfileUiState> { ex -> emit(ProfileUiState.Error(ex)) }
        .flowOn(Dispatchers.Default)
}