package org.example.project.domain.model

data class UserProfile(
    val id: String = "",
    val name: String = "Użytkownik",
    val email: String = "",
    val avatarUrl: String = ""
)