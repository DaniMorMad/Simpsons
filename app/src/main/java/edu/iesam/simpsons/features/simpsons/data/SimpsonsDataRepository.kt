package edu.iesam.simpsons.features.simpsons.data

import edu.iesam.simpsons.features.simpsons.data.remote.api.SimpsonsApiRemoteDataSource
import edu.iesam.simpsons.features.simpsons.domain.SimpsonsRepository
import edu.iesam.simpsons.features.simpsons.domain.Character

class SimpsonsDataRepository(private val remote: SimpsonsApiRemoteDataSource) : SimpsonsRepository {
    override suspend fun findAll(): Result<List<Character>> {
        return remote.getCharacters()
    }

    override suspend fun findById(id: Int): Result<Character> {
        return remote.getCharacterById(id)
    }
}