package com.robgar.marvel.core.usecase

import com.robgar.marvel.core.data.MarvelRepository
import javax.inject.Inject

class GetSuperheroesUseCase @Inject constructor(
    private val marvelRepository: MarvelRepository
) {
    suspend operator fun invoke() = marvelRepository.getSuperheroes()
}