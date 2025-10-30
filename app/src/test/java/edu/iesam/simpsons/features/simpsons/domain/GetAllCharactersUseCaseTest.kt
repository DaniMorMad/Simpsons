package edu.iesam.simpsons.features.simpsons.domain

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetAllCharactersUseCaseTest {

    @Test
    fun getAllCharactersUseCaseTest() = runTest {
        val characterList = listOf(
            Character(1, "name1", "occupation1", "url1"),
            Character(2, "name2", "occupation2", "url2"),
            Character(3, "name3", "occupation3", "url3")
        )
        val repository = mockk<SimpsonsRepository>()
        coEvery { repository.findAll() } returns Result.success(characterList)

        val useCase = GetAllCharactersUseCase(repository)
        val result = useCase()

        assert(result.isSuccess)
        assert(result.getOrNull() == characterList)
        coVerify(exactly = 1) { repository.findAll() }
        }

}