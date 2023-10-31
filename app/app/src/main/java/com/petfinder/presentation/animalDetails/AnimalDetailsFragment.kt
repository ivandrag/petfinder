package com.petfinder.presentation.animalDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.petfinder.databinding.FragmentAnimalDetailsBinding
import com.petfinder.presentation.BaseFragment
import com.petfinder.presentation.animalDetails.model.UiAnimalDetails
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnimalDetailsFragment : BaseFragment<FragmentAnimalDetailsBinding>() {

    private val animalDetailsViewModel: AnimalDetailsViewModel by viewModel()

    private val observer = Observer<AnimalDetailsViewModel.AnimalDetailsState> {
        when (it) {
            is AnimalDetailsViewModel.AnimalDetailsState.Success -> {
                showAnimalDetails(it.animalDetails)
            }
        }
    }

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAnimalDetailsBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt(ANIMAL_ID)
        animalDetailsViewModel.animalDetailsState.observe(viewLifecycleOwner, observer)

        animalDetailsViewModel.getAnimalDetails(id)
    }

    private fun showAnimalDetails(animalDetails: UiAnimalDetails) {
        val (name, breed, size, gender, status, distance) = animalDetails
        with(binding) {
            nameTextView.text = name
        }
    }

    companion object {
        const val ANIMAL_ID = "id"
    }
}
