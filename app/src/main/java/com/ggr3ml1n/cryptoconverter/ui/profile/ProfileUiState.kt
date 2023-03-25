package com.ggr3ml1n.cryptoconverter.ui.profile

import com.ggr3ml1n.cryptoconverter.model.Profile

data class ProfileUiState(
    val profile: Profile,
    val nightThemeMode: Boolean,
) {
    constructor() : this(profile = Profile(), nightThemeMode = false)
}