package com.petfinder.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.petfinder.data.remote.AnimalListPagingRemoteDataSource
import com.petfinder.domain.model.Animal
import com.petfinder.domain.repository.AnimalListRepository
import io.reactivex.rxjava3.core.Flowable

private const val PAGE_SIZE = 20

class AnimaListRepositoryImpl(
    private val animalListPagingRemoteDataSource: AnimalListPagingRemoteDataSource
) : AnimalListRepository {

    override fun getAllAnimals(): Flowable<PagingData<Animal>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { animalListPagingRemoteDataSource }
        ).flowable
    }
}
