package com.petfinder.presentation.animalList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.petfinder.databinding.FragmentAnimalListBinding
import com.petfinder.presentation.BaseFragment

class AnimalListFragment : BaseFragment<FragmentAnimalListBinding>() {

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAnimalListBinding.inflate(layoutInflater, container, false)
}
