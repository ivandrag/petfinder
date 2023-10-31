package com.petfinder.presentation.animalDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.petfinder.databinding.FragmentAnimalDetailsBinding
import com.petfinder.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnimalDetailsFragment : BaseFragment<FragmentAnimalDetailsBinding>() {

    private val animalDetailsViewModel: AnimalDetailsViewModel by viewModel()

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAnimalDetailsBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt(ANIMAL_ID)
        animalDetailsViewModel.getAnimalDetails(id)
    }

    companion object {
        const val ANIMAL_ID = "id"
    }
}
