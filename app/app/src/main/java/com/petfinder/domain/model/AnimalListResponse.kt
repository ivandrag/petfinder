package com.petfinder.domain.model

data class AnimalListResponse(
    val animals: List<Animal>,
    val pagination: Pagination
)

data class Animal(
    val id: Int,
    val organizationId: String,
    val url: String,
    val type: String,
    val species: String,
    val breeds: Breeds,
    val colors: Colors,
    val age: String,
    val gender: String,
    val size: String,
    val coat: String,
    val name: String,
    val description: String,
    val photos: List<Photo>,
    val videos: List<Video>,
    val status: String,
    val attributes: Attributes,
    val environment: Environment,
    val tags: List<String>,
    val contact: Contact,
    val publishedAt: String,
    val distance: Double
)

data class Breeds(
    val primary: String?,
    val secondary: String?,
    val mixed: Boolean,
    val unknown: Boolean
)

data class Colors(
    val primary: String?,
    val secondary: String?,
    val tertiary: String?
)

data class Photo(
    val small: String?,
    val medium: String?,
    val large: String?,
    val full: String?
)

data class Video(
    val embed: String?
)

data class Attributes(
    val spayedNeutered: Boolean,
    val houseTrained: Boolean,
    val declawed: Boolean,
    val specialNeeds: Boolean,
    val shotsCurrent: Boolean
)

data class Environment(
    val children: Boolean?,
    val dogs: Boolean?,
    val cats: Boolean?
)

data class Contact(
    val email: String?,
    val phone: String?,
    val address: Address
)

data class Address(
    val address1: String?,
    val address2: String?,
    val city: String?,
    val state: String?,
    val postcode: String?,
    val country: String?
)

data class Pagination(
    val countPerPage: Int,
    val totalCount: Int,
    val currentPage: Int,
    val totalPages: Int
)
