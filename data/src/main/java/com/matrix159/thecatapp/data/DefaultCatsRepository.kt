package com.matrix159.thecatapp.data

import com.matrix159.thecatapp.domain.repository.CatsRepository

internal class DefaultCatsRepository(
    private val catsRemoteDataSource: CatsRemoteDataSource
) : CatsRepository {
}