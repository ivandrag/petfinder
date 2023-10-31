package com.petfinder.data.repository

import com.petfinder.domain.repository.AnimalDetailsRepository
import com.petfinder.networking.PetFinderAPI

class AnimalDetailsRepositoryImpl(
    private val petFinderAPI: PetFinderAPI
) : AnimalDetailsRepository {

    override fun getAnimalDetails(id: Int) = petFinderAPI.getAnimalDetails(id)
}
