package com.robgar.marvel.core.usecase

import com.robgar.marvel.core.data.MarvelRepository
import com.robgar.marvel.core.data.network.model.Superhero
import com.robgar.marvel.core.data.network.model.SuperheroImage
import com.robgar.marvel.core.utils.flattenToList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetSuperheroesUseCaseTest {

    @RelaxedMockK
    private lateinit var marvelRepository: MarvelRepository

    lateinit var getSuperheroesUseCase: GetSuperheroesUseCase

    val superheroes = listOf<Superhero>(
        Superhero(1, "Spiderman", "", SuperheroImage("http://www.marvel.com", "jpg"), null, null),
        Superhero(2, "Batman", "", SuperheroImage("image_not_available", "jpg"), null, null),
        Superhero(3, "Wolverine", "", SuperheroImage("http://www.marvel.com", "jpg"), null, null),
        Superhero(4, "Antman", "", SuperheroImage("image_not_available", "jpg"), null, null),
        Superhero(5, "Ironman", "", SuperheroImage("http://www.marvel.com", "jpg"), null, null),
        Superhero(6, "Hulk", "", SuperheroImage("http://www.marvel.com", "jpg"), null, null)
    )

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getSuperheroesUseCase = GetSuperheroesUseCase(marvelRepository)
    }

    @Test
    fun `get superheroes returns success`() = runBlocking {
        // Given
        val superheroesList = superheroes
        coEvery { marvelRepository.getSuperheroes() } returns flowOf(superheroesList)

        // When
        val response = getSuperheroesUseCase()

        // Then
        assert(response.flattenToList().size == 6)
    }

}