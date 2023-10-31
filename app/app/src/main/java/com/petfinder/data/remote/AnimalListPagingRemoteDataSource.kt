package com.petfinder.data.remote

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.petfinder.domain.model.Animal
import com.petfinder.domain.model.AnimalListResponse
import com.petfinder.networking.PetFinderAPI
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class AnimalListPagingRemoteDataSource(
    private val petFinderAPI: PetFinderAPI
) : RxPagingSource<Int, Animal>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Animal>> {
        val position = params.key ?: 1

        println("##Key $position")

        return petFinderAPI.getAllAnimals(position)
            .subscribeOn(Schedulers.io())
            .map { response ->
                toLoadResult(response, position)
            }
            .onErrorReturn { e ->
                LoadResult.Error(e)
            }
    }

    override fun getRefreshKey(state: PagingState<Int, Animal>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun toLoadResult(response: AnimalListResponse, position: Int): LoadResult<Int, Animal> {
        return LoadResult.Page(
            data = response.animals,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == response.pagination.totalPages) null else position + 1
        )
    }
}