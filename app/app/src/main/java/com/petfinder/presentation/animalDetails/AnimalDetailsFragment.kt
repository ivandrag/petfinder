package com.petfinder.presentation.animalDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.petfinder.databinding.FragmentAnimalDetailsBinding
import com.petfinder.presentation.BaseFragment

class AnimalDetailsFragment : BaseFragment<FragmentAnimalDetailsBinding>() {

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAnimalDetailsBinding.inflate(layoutInflater, container, false)
}
