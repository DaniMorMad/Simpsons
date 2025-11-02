package edu.iesam.simpsons.features.simpsons.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.iesam.simpsons.R
import edu.iesam.simpsons.core.api.ApiClient
import edu.iesam.simpsons.features.simpsons.data.SimpsonsDataRepository
import edu.iesam.simpsons.features.simpsons.data.remote.api.SimpsonsApiRemoteDataSource
import edu.iesam.simpsons.features.simpsons.domain.GetAllCharactersUseCase

class SimpsonsListActivity : AppCompatActivity() {

    private val dataRepository = SimpsonsDataRepository(
        SimpsonsApiRemoteDataSource(ApiClient())
    )
    private val listViewModel = SimpsonsListViewModel(
        GetAllCharactersUseCase(dataRepository)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupObserver()
        listViewModel.loadAllSimpsons()
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
                Log.d("@dev", simpsons.toString())
            }
        }
    }
}