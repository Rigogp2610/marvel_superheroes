package com.robgar.marvel.core.data

import android.app.Application
import com.robgar.marvel.core.data.network.MarvelService
import com.robgar.marvel.core.data.network.model.Superhero
import com.robgar.marvel.core.data.network.model.SuperheroImage
import com.robgar.marvel.core.ui.superheroes.SuperheroesState
import com.robgar.marvel.core.ui.superheroes.SuperheroesViewModel
import com.robgar.marvel.core.usecase.GetSuperheroesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class MarvelRepositoryTest {

    @RelaxedMockK
    private lateinit var application: Application

    @RelaxedMockK
    private lateinit var marvelService: MarvelService

    private lateinit var marvelRepository: MarvelRepository

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
        marvelRepository = MarvelRepository(application, marvelService)
    }

    @Test
    fun `only get superheroes that have url image valid`() = runBlocking {
        // Given
        val superheroesList = superheroes
        coEvery { marvelRepository.getSuperheroes() } returns SuperheroesState.Success(superheroesList)

        // When
        val response = marvelRepository.getSuperheroes()

        // Then
        if (response is SuperheroesState.Success) {
            assert(response.superheroes.size == 4)
        }
    }
}