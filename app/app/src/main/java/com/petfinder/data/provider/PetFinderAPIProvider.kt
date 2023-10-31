package com.petfinder.data.provider

import com.petfinder.networking.PetFinderAPI

interface PetFinderAPIProvider {

    fun getPetFinderAPI(): PetFinderAPI
}
