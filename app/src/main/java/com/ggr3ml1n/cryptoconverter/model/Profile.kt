package com.ggr3ml1n.cryptoconverter.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

// TODO: add Avatar
@Parcelize
data class Profile(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
) : Parcelable {
    constructor() : this(-1, "Thomas", "Edison", "thomas@edison.ru", "thomas_edison_ru")

    @IgnoredOnParcel
    val fullName = "$firstName $lastName"
}