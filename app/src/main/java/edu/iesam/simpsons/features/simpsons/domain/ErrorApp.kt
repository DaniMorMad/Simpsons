package edu.iesam.simpsons.features.simpsons.domain

sealed class ErrorApp : Throwable() {
    object ServerError : ErrorApp()
}