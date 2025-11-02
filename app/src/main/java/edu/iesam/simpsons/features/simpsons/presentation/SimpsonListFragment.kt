package edu.iesam.simpsons.features.simpsons.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.simpsons.core.api.ApiClient
import edu.iesam.simpsons.databinding.FragmentSimpsonListBinding
import edu.iesam.simpsons.features.simpsons.data.SimpsonsDataRepository
import edu.iesam.simpsons.features.simpsons.data.remote.api.SimpsonsApiRemoteDataSource
import edu.iesam.simpsons.features.simpsons.domain.GetAllCharactersUseCase
import edu.iesam.simpsons.features.simpsons.presentation.adapter.SimpsonsAdapter

class SimpsonListFragment : Fragment() {

    private var _binding: FragmentSimpsonListBinding? = null
    private val binding get() = _binding!!

    private val simpsonsAdapter = SimpsonsAdapter()

    private val dataRepository = SimpsonsDataRepository(
        SimpsonsApiRemoteDataSource(ApiClient())
    )
    private val listViewModel = SimpsonsListViewModel(
        GetAllCharactersUseCase(dataRepository)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
        listViewModel.loadAllSimpsons()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimpsonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvSimpsonsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = simpsonsAdapter
        }
    }

    private fun setupObserver() {
        listViewModel.uiState.observe(this) { uiState ->
            if (uiState.isLoading) {
                //Muestro un spinner
            } else {
                //oculto spinner
            }

            uiState.error?.let { error ->
                Log.e("@dev", "Error: $error")
            }

            uiState.characters?.let { simpsons ->
                simpsonsAdapter.submitList(simpsons)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}