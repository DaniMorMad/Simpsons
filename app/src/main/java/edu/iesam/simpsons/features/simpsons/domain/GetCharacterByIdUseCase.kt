package edu.iesam.simpsons.features.simpsons.domain

class GetCharacterByIdUseCase(private val repository: SimpsonsRepository) {

    suspend operator fun invoke(id: Int): Result<Character> {
        return repository.findById(id)
    }

}