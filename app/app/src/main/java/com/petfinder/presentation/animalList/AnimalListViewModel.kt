package com.petfinder.presentation.animalList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.paging.map
import com.petfinder.domain.model.Animal
import com.petfinder.domain.repository.AnimalListRepository
import com.petfinder.presentation.animalDetails.BaseViewModel
import com.petfinder.presentation.animalList.model.UiAnimal
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AnimalListViewModel(
    private val animalListRepository: AnimalListRepository
) : BaseViewModel() {

    private val _animals = MutableLiveData<PagingData<UiAnimal>>()
    val animals: LiveData<PagingData<UiAnimal>> = _animals

    fun getAllAnimals() {
        disposable.add(animalListRepository.getAllAnimals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data -> _animals.value = mapToUiAnimalPagingData(data) },
                {}
            )
        )
    }

    private fun mapToUiAnimalPagingData(pagingData: PagingData<Animal>): PagingData<UiAnimal> {
        return pagingData.map { animal ->
            UiAnimal(
                id = animal.id,
                photo = animal.photos.firstOrNull()?.medium,
                name = animal.name,
                gender = animal.gender
            )
        }
    }
}
