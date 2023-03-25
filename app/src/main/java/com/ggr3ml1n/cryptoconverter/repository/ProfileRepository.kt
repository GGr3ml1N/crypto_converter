package com.ggr3ml1n.cryptoconverter.repository

import com.ggr3ml1n.cryptoconverter.model.Profile
import com.ggr3ml1n.cryptoconverter.temp.Database
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val database: Database,
) {

//   suspend fun getProfile(id: Long): Result<Profile> = database.getProfileById(id)
//        .flowOn(Dispatchers.IO)
//        .onStart { Result.Empty<Profile>() }
//        .map { Result.Success(it) }
//        .catch<Result<Profile>> { ex -> emit(Result.Error(ex)) }
//        .flowOn(Dispatchers.Default)

    suspend fun getCurrentProfile(id: Long): Profile = database.getProfileById(id)

}