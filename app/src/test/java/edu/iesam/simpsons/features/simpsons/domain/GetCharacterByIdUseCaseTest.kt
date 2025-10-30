package edu.iesam.simpsons.features.simpsons.domain

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetCharacterByIdUseCaseTest {

    @Test
    fun getCharacterByIdUseCaseTest() = runTest {
        val character = Character(1, "name","occupation", "url")
        val repository = mockk<SimpsonsRepository>()
        coEvery { repository.findById(1) } returns Result.success(character)

        val useCase = GetCharacterByIdUseCase(repository)
        val result = useCase(1)

        assert(result.isSuccess)
        assert(result.getOrNull() == character)
        coVerify(exactly = 1) { repository.findById(1) }
    }

}