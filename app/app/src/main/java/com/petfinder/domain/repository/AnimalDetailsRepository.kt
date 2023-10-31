package com.petfinder.domain.repository

import com.petfinder.domain.model.Animal
import com.petfinder.presentation.animalDetails.AnimalDetailsResponse
import io.reactivex.rxjava3.core.Single

interface AnimalDetailsRepository {

    fun getAnimalDetails(id: Int): Single<AnimalDetailsResponse>
}
