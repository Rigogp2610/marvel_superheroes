package com.robgar.marvel.core.utils

import kotlinx.serialization.Serializable

@Serializable
data class Envelope<A>(val code: Int, val attributionText: String, val data: A)

@Serializable
data class Paginated<A>(val results: List<A>)

typealias PaginatedEnvelope<A> = Envelope<Paginated<A>>