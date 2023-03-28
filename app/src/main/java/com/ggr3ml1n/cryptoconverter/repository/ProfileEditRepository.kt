package com.ggr3ml1n.cryptoconverter.repository

import com.ggr3ml1n.cryptoconverter.model.Profile
import com.ggr3ml1n.cryptoconverter.temp.Database
import javax.inject.Inject

class ProfileEditRepository @Inject constructor(
    private val database: Database,
) {
    suspend fun save(profile: Profile) = database.save(profile = profile)
}