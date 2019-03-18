package io.erie.repository.mapper

interface EntityMapper<T, V> {
    fun mapFromResponse(type: T): V
}