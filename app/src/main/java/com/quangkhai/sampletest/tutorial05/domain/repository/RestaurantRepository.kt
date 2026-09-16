package com.quangkhai.sampletest.tutorial05.domain.repository

import com.quangkhai.sampletest.tutorial05.domain.model.Restaurant

interface RestaurantRepository {
    suspend fun getRestaurants(): Result<List<Restaurant>>
    suspend fun addRestaurant(restaurant: Restaurant): Result<Unit>
}