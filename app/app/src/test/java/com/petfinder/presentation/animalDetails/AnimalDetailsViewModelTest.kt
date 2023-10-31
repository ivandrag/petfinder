package com.petfinder.presentation.animalDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.petfinder.domain.repository.AnimalDetailsRepository
import com.petfinder.presentation.animalDetails.model.UiAnimalDetails
import com.petfinder.utils.RxImmediateSchedulerRule
import com.petfinder.utils.TestDataFactory
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AnimalDetailsViewModelTest {

    @get:Rule
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val animalDetailsRepository: AnimalDetailsRepository = mockk()
    private val testDataFactory = TestDataFactory()
    private val observer: Observer<AnimalDetailsViewModel.AnimalDetailsState> = mockk()
    private val viewModel = AnimalDetailsViewModel(animalDetailsRepository)

    @Before
    fun setUp() {
        viewModel.animalDetailsState.observeForever(observer)
    }

    @Test
    fun `getAnimalDetails when success then state is Success`() {
        val id = 1
        val animalDetailsResponse = mockk<AnimalDetailsResponse>()
        val animal = testDataFactory.createAnimal()
        val uiAnimalDetails = UiAnimalDetails(
            name = animal.name,
            breed = animal.breeds.primary.orEmpty(),
            size = animal.size,
            gender = animal.gender,
            status = animal.status,
            distance = animal.distance.toString()
        )

        every { animalDetailsResponse.animal } returns testDataFactory.createAnimal()
        every { observer.onChanged(any()) } just Runs
        every {
            animalDetailsRepository.getAnimalDetails(id)
        } returns Single.just(animalDetailsResponse)

        viewModel.getAnimalDetails(id)

        verify {
            observer.onChanged(AnimalDetailsViewModel.AnimalDetailsState.Success(uiAnimalDetails))
        }
    }

    @Test
    fun `getAnimalDetails when error then state is Error`() {
        val id = 1
        val exception = Exception()

        every { observer.onChanged(any()) } just Runs
        every {
            animalDetailsRepository.getAnimalDetails(id)
        } returns Single.error(exception)

        viewModel.getAnimalDetails(id)

        verify {
            observer.onChanged(AnimalDetailsViewModel.AnimalDetailsState.Error)
        }
    }
}
