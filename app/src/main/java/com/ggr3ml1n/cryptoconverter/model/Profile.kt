package com.ggr3ml1n.cryptoconverter.model

// TODO: add Avatar
data class Profile(
    val id: Long,
    val firstName: String,
    val secondName: String,
    val email: String,
    val password: String
) {
    val fullName = firstName + secondName
}