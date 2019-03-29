package io.erie.network

/**
 * Class non-primitive objects, which Retrofit doesn't handle
 */

class Sources(private val sources: List<String>) {
    override fun toString(): String = sources.joinToString(",")
}