package org.example.project.domain.usecase

import org.example.project.domain.model.UserProfile
import org.example.project.domain.repository.UserProfileRepository

class GetUserProfileUseCase(
    private val userProfileRepository: UserProfileRepository
) {
    operator fun invoke(): UserProfile = userProfileRepository.getProfile()
}