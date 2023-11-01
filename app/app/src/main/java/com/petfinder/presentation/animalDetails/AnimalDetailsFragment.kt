package com.petfinder.presentation.animalDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.petfinder.R
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

            AnimalDetailsViewModel.AnimalDetailsState.Error -> {
                // TODO
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
            breedTextView.text = String.format(getString(R.string.animal_details_fragment_breed_text), breed)
            sizeTextView.text = String.format(getString(R.string.animal_details_fragment_size_text), size)
            genderTextView.text = String.format(getString(R.string.animal_details_fragment_gender_text), gender)
            statusTextView.text = String.format(getString(R.string.animal_details_fragment_status_text), status)
            distanceTextView.text = String.format(getString(R.string.animal_details_fragment_distance_text), distance)
        }
    }

    companion object {
        const val ANIMAL_ID = "id"
    }
}
