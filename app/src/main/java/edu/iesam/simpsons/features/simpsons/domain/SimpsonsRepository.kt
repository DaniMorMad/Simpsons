package edu.iesam.simpsons.features.simpsons.domain

interface SimpsonsRepository {
    suspend fun findAll(): Result<List<Character>>
}