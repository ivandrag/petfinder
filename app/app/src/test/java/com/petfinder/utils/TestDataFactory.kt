package com.petfinder.utils

import com.petfinder.domain.model.Address
import com.petfinder.domain.model.Animal
import com.petfinder.domain.model.Attributes
import com.petfinder.domain.model.Breeds
import com.petfinder.domain.model.Colors
import com.petfinder.domain.model.Contact
import com.petfinder.domain.model.Environment
import com.petfinder.domain.model.Photo
import com.petfinder.domain.model.Video

class TestDataFactory {

    fun createAnimal(distance: Double): Animal {
        val photo = Photo(
            small = "small.jpg",
            medium = "medium.jpg",
            large = "large.jpg",
            full = "full.jpg"
        )

        val video = Video(
            embed = "<iframe src='https://example.com/video/1'></iframe>"
        )

        val breeds = Breeds(
            primary = "Labrador",
            secondary = "Poodle",
            mixed = true,
            unknown = false
        )

        val colors = Colors(
            primary = "Black",
            secondary = "White",
            tertiary = null
        )

        val attributes = Attributes(
            spayedNeutered = true,
            houseTrained = true,
            declawed = false,
            specialNeeds = false,
            shotsCurrent = true
        )

        val environment = Environment(
            children = true,
            dogs = true,
            cats = true
        )

        val address = Address(
            address1 = "123 Main St",
            address2 = null,
            city = "Anytown",
            state = "Anystate",
            postcode = "12345",
            country = "USA"
        )

        val contact = Contact(
            email = "contact@example.com",
            phone = "123-456-7890",
            address = address
        )

        return Animal(
            id = 1,
            organizationId = "org123",
            url = "https://example.com/animal/1",
            type = "Dog",
            species = "Canine",
            breeds = breeds,
            colors = colors,
            age = "Adult",
            gender = "Female",
            size = "Medium",
            coat = "Short",
            name = "Luna",
            description = "A playful and friendly dog.",
            photos = listOf(photo),
            videos = listOf(video),
            status = "Available",
            attributes = attributes,
            environment = environment,
            tags = listOf("Playful", "Friendly", "Good with kids"),
            contact = contact,
            publishedAt = "2020-01-01T00:00:00Z",
            distance = distance
        )
    }
}