package com.ggr3ml1n.cryptoconverter.ui.profile

import com.ggr3ml1n.cryptoconverter.model.Profile

sealed class ProfileEffect {
    data class Success(val profile: Profile) : ProfileEffect()
    data class Error(val error: Throwable) : ProfileEffect()
}