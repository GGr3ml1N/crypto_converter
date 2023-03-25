package com.ggr3ml1n.cryptoconverter.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val nightThemeModeFlow: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[NIGHT_THEME_MODE] ?: false
        }

    val profileIdFlow: Flow<Long> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[PROFILE_ID] ?: -1L
        }

    suspend fun updateNightTheme(nightThemeMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[NIGHT_THEME_MODE] = nightThemeMode
        }
    }


    suspend fun updateUserId(userId: Long) {
        dataStore.edit { preferences ->
            preferences[PROFILE_ID] = userId
        }
    }

    companion object {
        val PROFILE_ID = longPreferencesKey("profile_id")
        val NIGHT_THEME_MODE = booleanPreferencesKey("night_theme_mode")
    }
}