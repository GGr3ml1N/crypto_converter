package com.ggr3ml1n.cryptoconverter.temp

import com.ggr3ml1n.cryptoconverter.model.Profile
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Database @Inject constructor() {

    private val profiles: Map<Long, Profile> = mapOf(
        1L to Profile(1, "Thomas", "Edison", "thomas@edison.ru", "thomas_edison_ru"),
        2L to Profile(2, "Alexey", "Dmitriev", "alexey@dmitriev.ru", "alexey_dmitriev_ru")
    )

    fun getProfileById(id: Long): Profile = profiles[id]!!
}