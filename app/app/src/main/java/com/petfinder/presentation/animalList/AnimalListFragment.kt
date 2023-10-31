package com.petfinder.presentation.animalList

import AnimalAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.petfinder.R
import com.petfinder.databinding.FragmentAnimalListBinding
import com.petfinder.presentation.BaseFragment
import com.petfinder.presentation.animalDetails.AnimalDetailsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnimalListFragment : BaseFragment<FragmentAnimalListBinding>() {

    private val animalListViewModel: AnimalListViewModel by viewModel()
    private lateinit var animalAdapter : AnimalAdapter

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAnimalListBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animalAdapter = AnimalAdapter {
            findNavController().navigate(R.id.animal_list_to_details_fragment, bundleOf(
                AnimalDetailsFragment.ANIMAL_ID to it
            ))
        }
        binding.animalListRecyclerView.adapter = animalAdapter
        binding.animalListRecyclerView.layoutManager = LinearLayoutManager(context)

        animalListViewModel.animals.observe(viewLifecycleOwner) { pagingData ->
            animalAdapter.submitData(lifecycle, pagingData)
        }

        animalListViewModel.getAllAnimals()
    }
}
