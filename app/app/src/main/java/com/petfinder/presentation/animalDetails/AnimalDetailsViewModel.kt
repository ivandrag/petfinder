package com.petfinder.presentation.animalDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.petfinder.domain.repository.AnimalDetailsRepository
import com.petfinder.presentation.animalDetails.model.UiAnimalDetails
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AnimalDetailsViewModel(
    private val animalDetailsRepository: AnimalDetailsRepository
) : BaseViewModel() {

    private val _animalDetailsState = MutableLiveData<AnimalDetailsState>()
    val animalDetailsState: LiveData<AnimalDetailsState> = _animalDetailsState

    fun getAnimalDetails(id: Int?) {
        id?.let {
            disposable.add(
                animalDetailsRepository.getAnimalDetails(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(::handleSuccess, ::handleError)
            )
        }
    }

    private fun handleSuccess(animalDetailsResponse: AnimalDetailsResponse) {
        val animal = animalDetailsResponse.animal
        _animalDetailsState.value = AnimalDetailsState.Success(
            UiAnimalDetails(
                name = animal.name,
                breed = animal.breeds.primary.orEmpty(),
                size = animal.size,
                gender = animal.gender,
                status = animal.status,
                distance = animal.distance.toString()
            )
        )
    }

    private fun handleError(throwable: Throwable) {
        _animalDetailsState.value = AnimalDetailsState.Error
    }

    sealed interface AnimalDetailsState {
        data class Success(val animalDetails: UiAnimalDetails) : AnimalDetailsState
        data object Error : AnimalDetailsState
    }
}
