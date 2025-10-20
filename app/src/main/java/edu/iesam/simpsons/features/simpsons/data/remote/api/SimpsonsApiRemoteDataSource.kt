package edu.iesam.simpsons.features.simpsons.data.remote.api

import edu.iesam.simpsons.core.api.ApiClient
import edu.iesam.simpsons.features.simpsons.domain.ErrorApp
import edu.iesam.simpsons.features.simpsons.domain.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SimpsonsApiRemoteDataSource(private val apiClient: ApiClient) {

    suspend fun getCharacters(): Result<List<Character>> {
        return withContext(Dispatchers.IO){
            val apiService = apiClient.createService(SimpsonsApiService::class.java)

            val resultSimpsons = apiService.findAll()

            if(resultSimpsons.isSuccessful && resultSimpsons.errorBody() == null){
                val simpsonsApiModel : SimpsonsApiModel = resultSimpsons.body()!!
                val listCharacterApiModel : List<CharacterModel> = simpsonsApiModel.results

                val listCharacter = listCharacterApiModel.map { characterApiModel ->
                    characterApiModel.toModel()
                }
                Result.success(listCharacter)
            } else {
                Result.failure(ErrorApp.ServerError)
            }
        }

    }
}