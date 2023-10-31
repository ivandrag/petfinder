package com.petfinder.presentation.animalList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import com.petfinder.domain.repository.AnimalListRepository
import com.petfinder.presentation.animalList.model.UiAnimal
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AnimalListViewModel(
    private val animalListRepository: AnimalListRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _animals = MutableLiveData<PagingData<UiAnimal>>()
    val animals: LiveData<PagingData<UiAnimal>> = _animals

    fun getAllAnimals() {
        disposable.add(animalListRepository.getAllAnimals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { pagingData ->
                pagingData.map { animal ->
                    UiAnimal(
                        id = animal.id,
                        type = animal.type,
                        species = animal.species,
                    )
                }
            }
            .cachedIn(viewModelScope)
            .subscribe { uiPagingData ->
                _animals.value = uiPagingData
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}