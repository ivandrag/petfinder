package com.petfinder.presentation.animalDetails

import com.petfinder.domain.repository.AnimalDetailsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AnimalDetailsViewModel(
    private val animalDetailsRepository: AnimalDetailsRepository
) : BaseViewModel() {

    fun getAnimalDetails(id: Int?) {
        id?.let {
            disposable.add(animalDetailsRepository
                .getAnimalDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { animal, t2 ->
                    println("##Animal Details $animal")
                }
            )
        }
    }
}
