package com.quangkhai.sampletest.tutorial05.domain.usecase

import com.quangkhai.sampletest.tutorial05.domain.model.Restaurant
import com.quangkhai.sampletest.tutorial05.domain.repository.RestaurantRepository

class AddRestaurantUseCase(private val repository: RestaurantRepository) {
    suspend operator fun invoke(restaurant: Restaurant): Result<Unit> =
        repository.addRestaurant(restaurant)
}