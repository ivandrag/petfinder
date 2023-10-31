package com.petfinder.domain.repository

import androidx.paging.PagingData
import com.petfinder.domain.model.Animal
import io.reactivex.rxjava3.core.Flowable

interface AnimalListRepository {

    fun getAllAnimals(): Flowable<PagingData<Animal>>
}
