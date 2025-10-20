package edu.iesam.simpsons.features.simpsons.domain

class GetAllCharactersUseCase(private val repository: SimpsonsRepository) {

    suspend operator fun invoke(): Result<List<Character>> {
        return repository.findAll()
    }


}