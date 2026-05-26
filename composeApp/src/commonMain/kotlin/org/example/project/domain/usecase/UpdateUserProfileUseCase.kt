package org.example.project.domain.usecase

import org.example.project.domain.model.UserProfile
import org.example.project.domain.repository.UserProfileRepository

class UpdateUserProfileUseCase(
    private val userProfileRepository: UserProfileRepository
) {
    operator fun invoke(profile: UserProfile) {
        userProfileRepository.updateProfile(profile)
    }
}