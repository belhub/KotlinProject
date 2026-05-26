package org.example.project.domain.repository

import org.example.project.domain.model.UserProfile

interface UserProfileRepository {
    fun getProfile(): UserProfile
    fun updateProfile(profile: UserProfile)
}