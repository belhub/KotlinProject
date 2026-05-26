package org.example.project.data.repository

import org.example.project.domain.model.UserProfile
import org.example.project.domain.repository.UserProfileRepository

class InMemoryUserProfileRepository : UserProfileRepository {
    private var profile = UserProfile(name = "Józef", email = "jozef@example.com")

    override fun getProfile(): UserProfile = profile

    override fun updateProfile(profile: UserProfile) {
        this.profile = profile
    }
}